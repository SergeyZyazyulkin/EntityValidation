package com.zyazyulkin.validation.test.entityvalidator;

import com.zyazyulkin.validation.EntityValidator;
import com.zyazyulkin.validation.test.util.TestWithConstraints;
import com.zyazyulkin.validation.verifier.ConstraintVerifier;
import com.zyazyulkin.validation.test.entityvalidator.entity.*;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation2;
import com.zyazyulkin.validation.util.Comparator;
import com.zyazyulkin.validation.violation.ConstraintViolation;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class EntityValidatorTest extends TestWithConstraints {

    private @NotNull List<ConstraintViolation> constraintViolationsAsList(ConstraintViolation... constraintViolations) {
        return constraintViolations != null ? Arrays.asList(constraintViolations) : new ArrayList<>();
    }

    private @NotNull ConstraintViolation makeConstraintViolation(
            @NotNull String field, ConstraintVerifier... constraintVerifiers) {

        List<ConstraintVerifier> constraintVerifiersList = constraintVerifiers != null ?
                Arrays.asList(constraintVerifiers) : new ArrayList<>();

        return new ConstraintViolation(field, constraintVerifiersList);
    }

    @Test
    public void testGetConstraintsOnNullObjectWithoutException() {
        List<ConstraintViolation> constraintViolations = EntityValidator.getConstraintViolations(null);
        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, Collections.emptyList()));
    }

    @Test
    public void testGetConstraintsOnObjectWithoutConstraints() {
        Object validating = new EntityWithoutConstraints();
        List<ConstraintViolation> constraintViolations = EntityValidator.getConstraintViolations(validating);
        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, Collections.emptyList()));
    }

    @Test
    public void testGetConstraintsOnValidObject() {
        Object validating = new ValidEntity();
        List<ConstraintViolation> constraintViolations = EntityValidator.getConstraintViolations(validating);
        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, Collections.emptyList()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetConstraintsOnObjectWithOneInvalidField() {
        Object validating = new EntityWithOneInvalidField();
        List<ConstraintViolation> constraintViolations = EntityValidator.getConstraintViolations(validating);

        List<ConstraintViolation> expected = constraintViolationsAsList(
                makeConstraintViolation("field", new AlwaysConstraintViolation()));

        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetConstraintsOnObjectWithOneValidAndThreeInvalidFields() {
        Object validating = new EntityWithThreeInvalidFields();
        List<ConstraintViolation> constraintViolations = EntityValidator.getConstraintViolations(validating);

        List<ConstraintViolation> expected = constraintViolationsAsList(
                makeConstraintViolation("field1", new AlwaysConstraintViolation()),
                makeConstraintViolation("field3", new AlwaysConstraintViolation2()),
                makeConstraintViolation("field4", new AlwaysConstraintViolation(), new AlwaysConstraintViolation2()));

        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, expected));
    }

    @Test(expected = Exception.class)
    public void testExceptionOnGetConstraintsFromNull() throws Exception {
        EntityValidator.getConstraintViolations(null, Exception::new);
    }

    @Test
    public void testIsValidOnValidEntity() {
        Assert.assertTrue(EntityValidator.isValid(new ValidEntity()));
    }

    @Test
    public void testIsValidOnInvalidEntity() {
        Assert.assertFalse(EntityValidator.isValid(new InvalidEntity()));
    }

    @Test
    public void testIsValidOnValidationOnNull() {
        Assert.assertTrue(EntityValidator.isValid(null, true));
    }

    @Test
    public void testValidateOnValidEntity() {
        EntityValidator.validate(new ValidEntity(), constraintViolations -> null);
    }

    @Test(expected = Exception.class)
    public void testValidateOnInvalidEntity() throws Exception {
        EntityValidator.validate(new InvalidEntity(), constraintViolations -> new Exception());
    }

    @Test
    public void testValidateOnNullWithoutCustomException() throws Exception {
        EntityValidator.validate(null, constraintViolations -> new Exception());
    }

    @Test(expected = NullPointerException.class)
    public void testValidateOnNullWithCustomException() throws Exception {
        EntityValidator.validate(null, constraintViolations -> new Exception(), NullPointerException::new);
    }
}

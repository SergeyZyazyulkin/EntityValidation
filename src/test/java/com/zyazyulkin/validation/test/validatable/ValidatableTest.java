package com.zyazyulkin.validation.test.validatable;

import com.zyazyulkin.validation.test.util.TestWithConstraints;
import com.zyazyulkin.validation.verifier.ConstraintVerifier;
import com.zyazyulkin.validation.test.validatable.entity.*;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation2;
import com.zyazyulkin.validation.util.Comparator;
import com.zyazyulkin.validation.violation.ConstraintViolation;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidatableTest extends TestWithConstraints {

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
    public void testGetConstraintsOnObjectWithoutConstraints() {
        List<ConstraintViolation> constraintViolations = new EntityWithoutConstraints().getConstraintViolations();
        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, Collections.emptyList()));
    }

    @Test
    public void testGetConstraintsOnValidObject() {
        List<ConstraintViolation> constraintViolations = new ValidEntity().getConstraintViolations();
        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, Collections.emptyList()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetConstraintsOnObjectWithOneInvalidField() {
        List<ConstraintViolation> constraintViolations = new EntityWithOneInvalidField().getConstraintViolations();

        List<ConstraintViolation> expected = constraintViolationsAsList(
                makeConstraintViolation("field", new AlwaysConstraintViolation()));

        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetConstraintsOnObjectWithOneValidAndThreeInvalidFields() {
        List<ConstraintViolation> constraintViolations = new EntityWithThreeInvalidFields().getConstraintViolations();

        List<ConstraintViolation> expected = constraintViolationsAsList(
                makeConstraintViolation("field1", new AlwaysConstraintViolation()),
                makeConstraintViolation("field3", new AlwaysConstraintViolation2()),
                makeConstraintViolation("field4", new AlwaysConstraintViolation(), new AlwaysConstraintViolation2()));

        Assert.assertTrue(Comparator.isCollectionsEqual(constraintViolations, expected));
    }

    @Test
    public void testIsValidOnValidEntity() {
        Assert.assertTrue(new ValidEntity().isValid());
    }

    @Test
    public void testIsValidOnInvalidEntity() {
        Assert.assertFalse(new InvalidEntity().isValid());
    }

    @Test
    public void testValidateOnValidEntity() {
        new ValidEntity().validate(constraintViolations -> null);
    }

    @Test(expected = Exception.class)
    public void testValidateOnInvalidEntity() throws Exception {
        new InvalidEntity().validate(constraintViolations -> new Exception());
    }
}

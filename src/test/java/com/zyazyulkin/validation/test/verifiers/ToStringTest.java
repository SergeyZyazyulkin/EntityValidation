package com.zyazyulkin.validation.test.verifiers;

import com.zyazyulkin.validation.EntityValidator;
import com.zyazyulkin.validation.test.verifiers.entity.EntityWithSeveralConstraints;
import com.zyazyulkin.validation.test.verifiers.entity.TestEnum;
import com.zyazyulkin.validation.verifier.*;
import com.zyazyulkin.validation.verifier.impl.*;
import org.junit.Assert;
import org.junit.Test;

public class ToStringTest {

    private static final ConstraintTarget[] constraintTargets = {ConstraintTarget.FIELD};

    @Test
    public void testNotNullToString() {
        Assert.assertEquals("NotNull([field])", new NotNullVerifier(constraintTargets).toString());
    }

    @Test
    public void testMinToString() {
        Assert.assertEquals("Min([3.0],[field])", new MinVerifier(constraintTargets, 3).toString());
    }

    @Test
    public void testMaxToString() {
        Assert.assertEquals("Max([-1.0],[field])", new MaxVerifier(constraintTargets, -1).toString());
    }

    @Test
    public void testMinLengthToString() {
        Assert.assertEquals("MinLength([4],[field])", new MinLengthVerifier(constraintTargets, 4).toString());
    }

    @Test
    public void testMaxLengthToString() {
        Assert.assertEquals("MaxLength([1],[field])", new MaxLengthVerifier(constraintTargets, 1).toString());
    }

    @Test
    public void testValidToString() {
        Assert.assertEquals("Valid([field])", new ValidVerifier(constraintTargets).toString());
    }

    @Test
    public void testMatchesToString() {
        Assert.assertEquals("Matches([^asd(a|n)*$],[field])",
                new MatchesVerifier(constraintTargets, "^asd(a|n)*$").toString());
    }

    @Test
    public void testContainsEnumToString() {
        ConstraintTarget[] constraintTargets = new ConstraintTarget[] { ConstraintTarget.ALL };

        Assert.assertEquals("ContainsEnum([case insensitive],[AAA,BBB,CCC],[field,collection_element])",
                new ContainsEnumVerifier(constraintTargets, false, TestEnum.class).toString());
    }

    @Test
    public void testConsistsOfToString() {
        Assert.assertEquals("ConsistsOf([12345],[field])",
                new ConsistsOfVerifier(constraintTargets, "12345").toString());
    }

    @Test
    public void testContainsDateToString() {
        Assert.assertEquals("ContainsDate([yyyy],[field])",
                new ContainsDateVerifier(constraintTargets, "yyyy").toString());
    }

    @Test
    public void testConstraintViolationToString() {
        EntityWithSeveralConstraints invalidEntity = new EntityWithSeveralConstraints("zxczxczcx");
        String toString = EntityValidator.getConstraintViolations(invalidEntity).get(0).toString();
        Assert.assertEquals("field ~ MaxLength([4],[field]); ConsistsOf([123456789],[field])", toString);
    }
}

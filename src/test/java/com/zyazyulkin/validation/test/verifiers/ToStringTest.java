package com.zyazyulkin.validation.test.verifiers;

import com.zyazyulkin.validation.test.verifiers.entity.TestEnum;
import com.zyazyulkin.validation.verifier.*;
import org.junit.Assert;
import org.junit.Test;

public class ToStringTest {

    @Test
    public void testNotNullToString() {
        Assert.assertEquals("NotNull(field)", new NotNullVerifier().toString());
    }

    @Test
    public void testMinToString() {
        Assert.assertEquals("Min(3.0)", new MinVerifier(3).toString());
    }

    @Test
    public void testMaxToString() {
        Assert.assertEquals("Max(-1.0)", new MaxVerifier(-1).toString());
    }

    @Test
    public void testMinLengthToString() {
        Assert.assertEquals("MinLength(4)", new MinLengthVerifier(4).toString());
    }

    @Test
    public void testMaxLengthToString() {
        Assert.assertEquals("MaxLength(1)", new MaxLengthVerifier(1).toString());
    }

    @Test
    public void testValidToString() {
        Assert.assertEquals("Valid(field)", new ValidVerifier().toString());
    }

    @Test
    public void testRegexToString() {
        Assert.assertEquals("Regex(^asd(a|n)*$)", new RegexVerifier("^asd(a|n)*$").toString());
    }

    @Test
    public void testEnumToString() {
        Assert.assertEquals("Enum([AAA, BBB, CCC])", new EnumVerifier(TestEnum.class).toString());
    }

    @Test
    public void testCharactersToString() {
        Assert.assertEquals("Characters(12345)", new CharactersVerifier("12345").toString());
    }
}

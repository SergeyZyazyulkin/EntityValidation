package com.zyazyulkin.validation.test.verifiers;

import com.zyazyulkin.validation.EntityValidator;
import com.zyazyulkin.validation.test.verifiers.entity.*;
import org.junit.Assert;
import org.junit.Test;

public class VerifiersTest {

    private void test(Object object, boolean expected) {
        Assert.assertEquals(expected, EntityValidator.isValid(object));
    }

    @Test
    public void testNotNullValid() {
        test(new EntityWithNotNull("asd"), true);
    }

    @Test
    public void testNotNullInvalid() {
        test(new EntityWithNotNull(null), false);
    }

    @Test
    public void testMinValid() {
        test(new EntityWithMin(-1000), true);
    }

    @Test
    public void testMinInvalid() {
        test(new EntityWithMin(-1001), false);
    }

    @Test
    public void testMaxValid() {
        test(new EntityWithMax(1000), true);
    }

    @Test
    public void testMaxInvalid() {
        test(new EntityWithMax(1001), false);
    }

    @Test
    public void testMinLengthValid() {
        test(new EntityWithMinLength("aaaaa"), true);
    }

    @Test
    public void testMinLengthInvalid() {
        test(new EntityWithMinLength("aaaa"), false);
    }

    @Test
    public void testMaxLengthValid() {
        test(new EntityWithMaxLength("aaaaaa"), true);
    }

    @Test
    public void testMaxLengthInvalid() {
        test(new EntityWithMaxLength("aaaaaaa"), false);
    }

    @Test
    public void testValidValid() {
        test(new EntityWithValid(new EntityWithNotNull("asd")), true);
    }

    @Test
    public void testValidInvalid() {
        test(new EntityWithValid(new EntityWithNotNull(null)), false);
    }

    @Test
    public void testRegexValid() {
        test(new EntityWithRegex("abd"), true);
    }

    @Test
    public void testRegexInvalid() {
        test(new EntityWithRegex("abc"), false);
    }

    @Test
    public void testEnumValid() {
        test(new EntityWithEnum("cCc"), true);
    }

    @Test
    public void testEnumInvalid() {
        test(new EntityWithEnum("ZZz"), false);
    }

    @Test
    public void testCharactersValid() {
        test(new EntityWithCharacters("abcba"), true);
    }

    @Test
    public void testCharactersInvalid() {
        test(new EntityWithCharacters("123178012748"), false);
    }

    @Test
    public void testDateInvalid() {
        test(new EntityWithDate("21 02 1996"), false);
    }

    @Test
    public void testDateValid() {
        test(new EntityWithDate("21.02.1996"), true);
    }

    @Test
    public void testSeveralValid() {
        test(new EntityWithSeveralConstraints("1234"), true);
    }

    @Test
    public void testSeveralOneInvalid() {
        test(new EntityWithSeveralConstraints("12345"), false);
    }

    @Test
    public void testSeveralManyInvalid() {
        test(new EntityWithSeveralConstraints("akjshdksajhdksaj"), false);
    }

    @Test
    public void testConstraintsValid() {
        test(new EntityWithConstraints("aa", 0, new EntityWithNotNull("a"), "bbb", "321"), true);
    }

    @Test
    public void testConstraintsOneInvalid() {
        test(new EntityWithConstraints("bb", 0, new EntityWithNotNull("b"), "asd", "222"), false);
    }

    @Test
    public void testConstraintsAllInvalid() {
        test(new EntityWithConstraints("zx", -1000, new EntityWithNotNull(null), "dsa", "666"), false);
    }
}

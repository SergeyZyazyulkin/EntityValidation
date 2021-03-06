package com.zyazyulkin.validation.test.verifiers;

import com.zyazyulkin.validation.EntityValidator;
import com.zyazyulkin.validation.test.verifiers.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
    public void testMatchesValid() {
        test(new EntityWithMatches("abd"), true);
    }

    @Test
    public void testMatchesInvalid() {
        test(new EntityWithMatches("abc"), false);
    }

    @Test
    public void testContainsEnumValid() {
        test(new EntityWithContainsEnum("cCC"), true);
    }

    @Test
    public void testContainsEnumInvalid() {
        test(new EntityWithContainsEnum("zzZ"), false);
    }

    @Test
    public void testConsistsOfValid() {
        test(new EntityWithConsistsOf("abcba"), true);
    }

    @Test
    public void testConsistsOfInvalid() {
        test(new EntityWithConsistsOf("123178012748"), false);
    }

    @Test
    public void testContainsDateInvalid() {
        test(new EntityWithContainsDate("21 02 1996"), false);
    }

    @Test
    public void testContainsDateValid() {
        test(new EntityWithContainsDate("21.02.1996"), true);
    }

    @Test
    public void testCollectionInvalidField() {
        test(new EntityWithCollectionConstraints(null), false);
    }

    @Test
    public void testCollectionInvalidOneElement() {
        test(new EntityWithCollectionConstraints(Arrays.asList("1", null, "2")), false);
    }

    @Test
    public void testCollectionInvalidAllElements() {
        test(new EntityWithCollectionConstraints(Arrays.asList(null, null, null, null)), false);
    }

    @Test
    public void testCollectionValid() {
        test(new EntityWithCollectionConstraints(Arrays.asList("1", "2", "3", "4", "5")), true);
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
        test(new EntityWithConstraints("aa", 0, new EntityWithNotNull("a"), "BBB", "321"), true);
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

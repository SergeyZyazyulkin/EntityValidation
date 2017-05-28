package com.zyazyulkin.validation.test;

import com.zyazyulkin.validation.util.ExceptionUtil;
import org.junit.Test;

public class ExceptionUtilTest {

    @Test
    public void testNullSupplier() {
        ExceptionUtil.extractAndThrow(null);
    }

    @Test
    public void testNullException() {
        ExceptionUtil.extractAndThrow(() -> null);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowing() {
        ExceptionUtil.extractAndThrow(NullPointerException::new);
    }
}

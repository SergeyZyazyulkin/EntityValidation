package com.zyazyulkin.validation.test.objectutil;

import com.zyazyulkin.validation.test.objectutil.entity.EntityWithoutFields;
import com.zyazyulkin.validation.test.objectutil.entity.SonWithOneField;
import com.zyazyulkin.validation.util.ObjectUtil;
import org.junit.Assert;
import org.junit.Test;

public class ObjectUtilTest {

    @Test
    public void testAllNull() {
        Assert.assertFalse(ObjectUtil.isAllNotNull(null, null));
    }

    @Test
    public void testOneNull() {
        Assert.assertFalse(ObjectUtil.isAllNotNull(null, "test"));
    }

    @Test
    public void testAllNotNull() {
        Assert.assertTrue(ObjectUtil.isAllNotNull("test1", "test2"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionOnNull() {
        ObjectUtil.nullPointerExceptionOnNull(null);
    }

    @Test
    public void testNoNullPointerExceptionOnNotNull() {
        ObjectUtil.nullPointerExceptionOnNull("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomExceptionOnNull() {
        ObjectUtil.requireNotNull(null, IllegalArgumentException::new);
    }

    @Test
    public void testNoCustomExceptionOnNotNull() {
        ObjectUtil.requireNotNull("test", IllegalArgumentException::new);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionOnAllNull() {
        ObjectUtil.nullPointerExceptionOnAtLeastOneNull(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionOnOneNull() {
        ObjectUtil.nullPointerExceptionOnAtLeastOneNull("test", null);
    }

    @Test
    public void testNoNullPointerExceptionOnAllNotNull() {
        ObjectUtil.nullPointerExceptionOnAtLeastOneNull("test1", "test2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomExceptionOnAllNull() {
        ObjectUtil.requireAllNotNull(IllegalArgumentException::new, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomExceptionOnOneNull() {
        ObjectUtil.requireAllNotNull(IllegalArgumentException::new, null, "test");
    }

    @Test
    public void testNoCustomExceptionOnAllNotNull() {
        ObjectUtil.requireAllNotNull(IllegalArgumentException::new, "test1", "test2");
    }

    @Test
    public void testFieldExtraction() {
        Assert.assertEquals(3, ObjectUtil.extractFields(new SonWithOneField()).size());
    }

    @Test
    public void testFieldExtractionFromEntityWithoutFields() {
        Assert.assertEquals(0, ObjectUtil.extractFields(new EntityWithoutFields()).size());
    }
}

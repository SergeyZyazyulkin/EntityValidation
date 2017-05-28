package com.zyazyulkin.validation.test;

import com.zyazyulkin.validation.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ComparatorTest {

    @Test
    public void testDifferentValueTypes() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3), Arrays.asList("1", "2", "3")));
    }

    @Test
    public void testDifferentTypes() {
        Assert.assertTrue(Comparator.isCollectionsEqual(Collections.emptyList(), Collections.emptySet()));
    }

    @Test
    public void testDifferentValues() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));
    }

    @Test
    public void testDifferentValue() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 4)));
    }

    @Test
    public void testExtraValue() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void testEmptyEqual() {
        Assert.assertTrue(Comparator.isCollectionsEqual(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    public void testDifferentCardinalityDifferentSize() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 3)));
    }

    @Test
    public void testDifferentCardinalitySameSize() {
        Assert.assertFalse(Comparator.isCollectionsEqual(Arrays.asList(1, 2, 3, 3), Arrays.asList(1, 2, 2, 3)));
    }

    @Test
    public void testDifferentOrder() {
        Assert.assertTrue(Comparator.isCollectionsEqual(Arrays.asList(1, 1, 2, 3), Arrays.asList(3, 1, 2, 1)));
    }

    @Test
    public void testOneNull() {
        Assert.assertFalse(Comparator.isCollectionsEqual(null, Arrays.asList(4, 5, 6)));
    }

    @Test
    public void testNullCollectionsEqual() {
        Assert.assertTrue(Comparator.isCollectionsEqual(null, null));
    }
}

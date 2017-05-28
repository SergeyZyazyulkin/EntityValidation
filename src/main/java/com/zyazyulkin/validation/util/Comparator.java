package com.zyazyulkin.validation.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

public class Comparator {

    public static <T> boolean isCollectionsEqual(Collection<T> collection1, Collection<T> collection2) {
        return collection1 == null && collection2 == null ||
                collection1 != null && collection2 != null &&
                        CollectionUtils.isEqualCollection(collection1, collection2);
    }
}

package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.ContainsEnum;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithContainsEnum {

    @ContainsEnum(value = TestEnum.class, caseSensitive = false)
    private String field;

    public EntityWithContainsEnum(String field) {
        this.field = field;
    }
}

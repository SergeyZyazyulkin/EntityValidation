package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.Enum;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithEnum {

    @Enum(TestEnum.class)
    private String field;

    public EntityWithEnum(String field) {
        this.field = field;
    }
}

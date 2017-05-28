package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.EnumConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithEnum {

    @EnumConstraint(TestEnum.class)
    private String field;

    public EntityWithEnum(String field) {
        this.field = field;
    }
}

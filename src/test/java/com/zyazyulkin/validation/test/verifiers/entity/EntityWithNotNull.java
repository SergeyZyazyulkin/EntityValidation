package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.NotNullConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithNotNull {

    @NotNullConstraint
    private String field;

    public EntityWithNotNull(String field) {
        this.field = field;
    }
}

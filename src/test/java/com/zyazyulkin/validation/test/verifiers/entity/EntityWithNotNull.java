package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.NotNull;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithNotNull {

    @NotNull
    private String field;

    public EntityWithNotNull(String field) {
        this.field = field;
    }
}

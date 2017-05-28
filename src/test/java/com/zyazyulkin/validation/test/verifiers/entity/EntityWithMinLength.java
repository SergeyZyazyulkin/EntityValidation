package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.MinLength;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMinLength {

    @MinLength(5)
    private String field;

    public EntityWithMinLength(String field) {
        this.field = field;
    }
}

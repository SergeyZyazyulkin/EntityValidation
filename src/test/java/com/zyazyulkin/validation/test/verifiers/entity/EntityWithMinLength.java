package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.MinLengthConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMinLength {

    @MinLengthConstraint(5)
    private String field;

    public EntityWithMinLength(String field) {
        this.field = field;
    }
}

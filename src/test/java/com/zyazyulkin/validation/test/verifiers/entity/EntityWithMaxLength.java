package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.MaxLengthConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMaxLength {

    @MaxLengthConstraint(6)
    private String field;

    public EntityWithMaxLength(String field) {
        this.field = field;
    }
}

package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.CharactersConstraint;
import com.zyazyulkin.validation.constraint.MaxLengthConstraint;
import com.zyazyulkin.validation.constraint.MinLengthConstraint;
import com.zyazyulkin.validation.constraint.NotNullConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithSeveralConstraints {

    @NotNullConstraint
    @MinLengthConstraint(3)
    @MaxLengthConstraint(4)
    @CharactersConstraint("123456789")
    private String field;

    public EntityWithSeveralConstraints(String field) {
        this.field = field;
    }
}

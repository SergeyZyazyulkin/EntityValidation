package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.ConsistsOf;
import com.zyazyulkin.validation.constraint.MaxLength;
import com.zyazyulkin.validation.constraint.MinLength;
import com.zyazyulkin.validation.constraint.NotNull;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithSeveralConstraints {

    @NotNull @MinLength(3) @MaxLength(4) @ConsistsOf(characters = "123456789")
    private String field;

    public EntityWithSeveralConstraints(String field) {
        this.field = field;
    }
}

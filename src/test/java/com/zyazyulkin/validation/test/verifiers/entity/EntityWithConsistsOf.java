package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.ConsistsOf;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithConsistsOf {

    @ConsistsOf(characters = "abc")
    private String field;

    public EntityWithConsistsOf(String field) {
        this.field = field;
    }
}

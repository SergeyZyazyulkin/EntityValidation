package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.Regex;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithRegex {

    @Regex("a(b|c)d")
    private String field;

    public EntityWithRegex(String field) {
        this.field = field;
    }
}

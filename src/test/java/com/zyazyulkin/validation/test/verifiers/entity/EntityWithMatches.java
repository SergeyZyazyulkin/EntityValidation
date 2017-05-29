package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.Matches;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMatches {

    @Matches(regex = "a(b|c)d")
    private String field;

    public EntityWithMatches(String field) {
        this.field = field;
    }
}

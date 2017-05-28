package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.CharactersConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithCharacters {

    @CharactersConstraint("abc")
    private String field;

    public EntityWithCharacters(String field) {
        this.field = field;
    }
}

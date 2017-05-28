package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.MinConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMin {

    @MinConstraint(-1000)
    private int field;

    public EntityWithMin(int field) {
        this.field = field;
    }
}

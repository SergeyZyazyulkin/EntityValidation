package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.MaxConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithMax {

    @MaxConstraint(1000)
    private int field;

    public EntityWithMax(int field) {
        this.field = field;
    }
}

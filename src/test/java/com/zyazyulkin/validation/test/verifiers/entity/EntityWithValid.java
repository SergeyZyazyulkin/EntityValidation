package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.Valid;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithValid {

    @Valid
    private EntityWithNotNull entityWithNotNull;

    public EntityWithValid(EntityWithNotNull entityWithNotNull) {
        this.entityWithNotNull = entityWithNotNull;
    }
}

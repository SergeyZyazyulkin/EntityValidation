package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.ValidConstraint;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithValid {

    @ValidConstraint
    EntityWithNotNull entityWithNotNull;

    public EntityWithValid(EntityWithNotNull entityWithNotNull) {
        this.entityWithNotNull = entityWithNotNull;
    }
}

package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.NotNull;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

import java.util.List;

@SuppressWarnings
public class EntityWithCollectionConstraints {

    @NotNull(targets = {ConstraintTarget.FIELD, ConstraintTarget.COLLECTION_ELEMENT})
    private List<String> field;

    public EntityWithCollectionConstraints(List<String> field) {
        this.field = field;
    }
}

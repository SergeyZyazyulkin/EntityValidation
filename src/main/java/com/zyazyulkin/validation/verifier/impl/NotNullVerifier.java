package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NotNullVerifier extends AbstractConstraintVerifier {

    public NotNullVerifier(@NotNull ConstraintTarget[] constraintTargets) {
        super(constraintTargets);
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, Objects::nonNull, false);
    }
}

package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.EntityValidator;
import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

public class ValidVerifier extends AbstractConstraintVerifier {

    public ValidVerifier(@NotNull ConstraintTarget[] constraintTargets) {
        super(constraintTargets);
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, EntityValidator::isValid);
    }
}

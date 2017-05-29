package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.EntityValidator;

public class ValidVerifier extends AbstractConstraintVerifier {

    public ValidVerifier() {
    }

    public ValidVerifier(ConstraintTarget[] constraintTarget) {
        super(constraintTarget);
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, EntityValidator::isValid);
    }

    @Override
    protected String getParametersForToString() {
        return getTargets();
    }
}

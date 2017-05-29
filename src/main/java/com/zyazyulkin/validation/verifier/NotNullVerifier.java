package com.zyazyulkin.validation.verifier;

import java.util.Objects;

public class NotNullVerifier extends AbstractConstraintVerifier {

    public NotNullVerifier() {
    }

    public NotNullVerifier(ConstraintTarget[] constraintTarget) {
        super(constraintTarget);
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, Objects::nonNull, false);
    }

    @Override
    protected String getParametersForToString() {
        return getTargets();
    }
}

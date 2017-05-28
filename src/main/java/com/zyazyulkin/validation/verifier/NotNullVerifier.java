package com.zyazyulkin.validation.verifier;

import java.util.Objects;

public class NotNullVerifier extends AbstractConstraintVerifier {

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, Objects::nonNull, false);
    }
}

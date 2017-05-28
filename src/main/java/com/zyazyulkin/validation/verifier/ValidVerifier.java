package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.EntityValidator;

public class ValidVerifier extends AbstractConstraintVerifier {

    @Override
    public boolean verify(Object value) {
        return verify(value, Object.class, EntityValidator::isValid);
    }
}

package com.zyazyulkin.validation.test.util.verifier;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;

public class AlwaysConstraintViolation extends AbstractConstraintVerifier {

    @Override
    public boolean verify(Object value) {
        return false;
    }
}

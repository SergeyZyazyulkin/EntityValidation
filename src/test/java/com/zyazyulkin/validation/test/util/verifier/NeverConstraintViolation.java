package com.zyazyulkin.validation.test.util.verifier;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintVerifier;

public class NeverConstraintViolation extends AbstractConstraintVerifier {

    @Override
    public boolean verify(Object value) {
        return true;
    }
}

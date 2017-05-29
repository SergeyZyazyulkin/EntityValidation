package com.zyazyulkin.validation.test.util.verifier;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;

public class AlwaysConstraintViolation extends AbstractConstraintVerifier {

    public AlwaysConstraintViolation() {
        super(new ConstraintTarget[] {ConstraintTarget.FIELD});
    }

    @Override
    public boolean verify(Object value) {
        return false;
    }
}

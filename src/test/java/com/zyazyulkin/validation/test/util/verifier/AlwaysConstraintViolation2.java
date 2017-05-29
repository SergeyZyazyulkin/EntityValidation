package com.zyazyulkin.validation.test.util.verifier;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import com.zyazyulkin.validation.verifier.ConstraintVerifier;

public class AlwaysConstraintViolation2 extends AbstractConstraintVerifier {

    public AlwaysConstraintViolation2() {
        super(new ConstraintTarget[] {ConstraintTarget.FIELD});
    }

    @Override
    public boolean verify(Object value) {
        return false;
    }
}

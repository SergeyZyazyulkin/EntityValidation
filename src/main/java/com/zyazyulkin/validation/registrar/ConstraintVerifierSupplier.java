package com.zyazyulkin.validation.registrar;

import com.zyazyulkin.validation.verifier.ConstraintVerifier;

import java.lang.annotation.Annotation;

public interface ConstraintVerifierSupplier<A extends Annotation> {

    ConstraintVerifier get(A annotation);
}

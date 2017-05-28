package com.zyazyulkin.validation.verifier;

@FunctionalInterface
public interface ConstraintVerifier {

    boolean verify(Object value);
}

package com.zyazyulkin.validation.verifier;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ConstraintVerifier {

    boolean verify(@NotNull Class<?> type, Object value);
}

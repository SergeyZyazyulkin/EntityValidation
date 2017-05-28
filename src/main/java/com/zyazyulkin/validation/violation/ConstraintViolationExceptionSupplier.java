package com.zyazyulkin.validation.violation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@FunctionalInterface
public interface ConstraintViolationExceptionSupplier<T extends Throwable> {

    T get(@NotNull List<ConstraintViolation> constraintViolations);
}

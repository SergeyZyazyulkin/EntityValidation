package com.zyazyulkin.validation;

import com.zyazyulkin.validation.exception.InternalErrorException;
import com.zyazyulkin.validation.violation.ConstraintViolation;
import com.zyazyulkin.validation.violation.ConstraintViolationExceptionSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Validatable {

    default <E extends Throwable> void validate(@NotNull ConstraintViolationExceptionSupplier<E> onConstraintViolation)
            throws E, InternalErrorException {

        EntityValidator.validate(this, onConstraintViolation);
    }

    default boolean isValid() throws InternalErrorException {
        return EntityValidator.isValid(this);
    }

    default @NotNull List<ConstraintViolation> getConstraintViolations() throws InternalErrorException {
        return EntityValidator.getConstraintViolations(this);
    }
}

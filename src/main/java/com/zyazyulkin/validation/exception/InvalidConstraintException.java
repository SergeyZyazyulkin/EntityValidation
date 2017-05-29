package com.zyazyulkin.validation.exception;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class InvalidConstraintException extends ValidationException {

    private static final String WRONG_TYPE_FORMAT = "Expected element type: %s";
    private static final String INVALID_PARAMETER = "Invalid parameter '%s': %s";

    public static @NotNull InvalidConstraintException invalidType(@NotNull String type) {
        return new InvalidConstraintException(String.format(WRONG_TYPE_FORMAT, type));
    }

    public static @NotNull InvalidConstraintException invalidParameter(@NotNull String name, @NotNull String value) {
        return new InvalidConstraintException(String.format(INVALID_PARAMETER, name, value));
    }

    public InvalidConstraintException() {
    }

    public InvalidConstraintException(@NonNls String message) {
        super(message);
    }

    public InvalidConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConstraintException(Throwable cause) {
        super(cause);
    }

    public InvalidConstraintException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

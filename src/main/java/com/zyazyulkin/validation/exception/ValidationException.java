package com.zyazyulkin.validation.exception;

import org.jetbrains.annotations.NonNls;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(@NonNls String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    protected ValidationException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

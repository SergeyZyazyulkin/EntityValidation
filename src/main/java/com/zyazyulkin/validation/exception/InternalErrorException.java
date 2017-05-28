package com.zyazyulkin.validation.exception;

import org.jetbrains.annotations.NonNls;

public class InternalErrorException extends ValidationException {

    public InternalErrorException() {
    }

    public InternalErrorException(@NonNls String message) {
        super(message);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalErrorException(Throwable cause) {
        super(cause);
    }

    public InternalErrorException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

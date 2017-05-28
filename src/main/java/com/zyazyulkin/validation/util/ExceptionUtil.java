package com.zyazyulkin.validation.util;

import java.util.function.Supplier;

public class ExceptionUtil {

    public static <E extends Throwable> void extractAndThrow(Supplier<E> supplier) throws E {
        if (supplier != null) {
            E throwingException = supplier.get();

            if (throwingException != null) {
                throw throwingException;
            }
        }
    }
}

package com.zyazyulkin.validation.util;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ObjectUtil {

    public static boolean isAllNotNull(Object... checkingObjects) {
        if (checkingObjects != null) {
            for (Object checkingObject : checkingObjects) {
                if (checkingObject == null) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static void nullPointerExceptionOnNull(Object object) throws NullPointerException {
        requireNotNull(object, NullPointerException::new);
    }

    public static <E extends Throwable> void requireNotNull(Object checking, @NotNull Supplier<E> onNull) throws E {
        if (checking == null) {
            E onNullException = onNull.get();

            if (onNullException != null) {
                throw onNullException;
            }
        }
    }

    public static void nullPointerExceptionOnAtLeastOneNull(Object... objects) throws NullPointerException {
        requireAllNotNull(NullPointerException::new, objects);
    }

    public static <E extends Throwable> void requireAllNotNull(
            @NotNull Supplier<E> onAtLeastOneNull, Object... checking)
            throws E {

        if (!isAllNotNull(checking)) {
            ExceptionUtil.extractAndThrow(onAtLeastOneNull);
        }
    }

    public static @NotNull List<Field> extractFields(@NotNull Object object) {
        List<Field> fields = new ArrayList<>();
        Class processing = object.getClass();

        do {
            Collections.addAll(fields, processing.getDeclaredFields());
            processing = processing.getSuperclass();
        } while (processing != null);

        return fields;
    }
}

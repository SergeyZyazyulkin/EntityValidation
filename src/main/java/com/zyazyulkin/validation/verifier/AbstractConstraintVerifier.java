package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.exception.InvalidConstraintException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractConstraintVerifier implements ConstraintVerifier {

    @SuppressWarnings("unchecked")
    protected  <T> T cast(Object object, @NotNull Class<T> resultingClass) throws InvalidConstraintException {
        if (object != null) {
            if (resultingClass.isAssignableFrom(object.getClass())) {
                return (T) object;
            } else {
                throw InvalidConstraintException.invalidType(resultingClass.getSimpleName());
            }
        } else {
            return null;
        }
    }

    protected <T> boolean verify(
            Object value, @NotNull Class<T> requiredClass, @NotNull Function<T, Boolean> verification) {

        return verify(value, requiredClass, verification, true);
    }

    protected <T> boolean verify(
            Object value,
            @NotNull Class<T> requiredClass,
            @NotNull Function<T, Boolean> verification,
            boolean onNull) {

        T castedValue = cast(value, requiredClass);
        return castedValue == null ? onNull : verification.apply(castedValue);
    }

    protected String getParametersForToString() {
        return "";
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && Objects.equals(getClass(), obj.getClass());
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();

        if (className.endsWith("Verifier")) {
            className = className.substring(0, className.length() - 8);
        }

        return String.format("%s(%s)", className, getParametersForToString());
    }
}

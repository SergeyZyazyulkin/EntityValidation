package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.exception.InvalidConstraintException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractConstraintVerifier implements ConstraintVerifier {

    @NotNull
    private ConstraintTarget[] targets;

    protected AbstractConstraintVerifier() {
        this(new ConstraintTarget[] { ConstraintTarget.FIELD});
    }

    protected AbstractConstraintVerifier(@NotNull ConstraintTarget[] targets) {
        this.targets = targets;
    }

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

    @Override
    public boolean verify(@NotNull Class<?> type, Object value) {
        List<ConstraintTarget> targets = Arrays.asList(this.targets);
        boolean valid = true;

        if (targets.contains(ConstraintTarget.FIELD)) {
            valid = verify(value);
        }

        if (valid && targets.contains(ConstraintTarget.COLLECTION_ELEMENT)) {
            Collection collection = cast(value, Collection.class);

            for (Object collectionElement : collection) {
                valid = valid && verify(collectionElement);
            }
        }

        return valid;
    }

    protected abstract boolean verify(Object value);

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

    protected String getTargets() {
        return Arrays.stream(targets)
                .map(ConstraintTarget::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractConstraintVerifier that = (AbstractConstraintVerifier) o;

        return Arrays.equals(targets, that.targets);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(targets);
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

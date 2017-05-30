package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.exception.InvalidConstraintException;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractConstraintVerifier implements ConstraintVerifier {

    @NotNull
    private List<ConstraintTarget> targets;

    protected AbstractConstraintVerifier(@NotNull ConstraintTarget[] targets) {
        this.targets = Arrays.asList(targets);
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

    private boolean shouldTarget(@NotNull ConstraintTarget target) {
        return targets.contains(ConstraintTarget.ALL) || targets.contains(target);
    }

    @Override
    public boolean verify(@NotNull Class<?> type, Object value) {
        boolean valid = true;

        if (shouldTarget(ConstraintTarget.FIELD)) {
            valid = verify(value);
        }

        if (valid && value != null && shouldTarget(ConstraintTarget.COLLECTION_ELEMENT)) {
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

    public String getName() {
        String className = getClass().getSimpleName();

        if (className.endsWith("Verifier")) {
            className = className.substring(0, className.length() - 8);
        }

        return className;
    }

    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();
        Collections.addAll(parameters, getParametersDescription());
        parameters.add(getTargetsDescription());
        return parameters;
    }

    protected String[] getParametersDescription() {
        return new String[0];
    }

    protected String getTargetsDescription() {
        List<ConstraintTarget> targets = new ArrayList<>(this.targets);

        if (targets.contains(ConstraintTarget.ALL)) {
            targets = Arrays.stream(ConstraintTarget.values())
                    .filter(constraintTarget -> constraintTarget != ConstraintTarget.ALL)
                    .collect(Collectors.toList());
        }

        String targetsDescription = targets.stream()
                .map(ConstraintTarget::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining(","));

        return String.format("%s", targetsDescription);
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

        return targets.equals(that.targets);
    }

    @Override
    public int hashCode() {
        return targets.hashCode();
    }

    @Override
    public String toString() {
        String parametersDescription = getParameters().stream()
                .map(description -> String.format("[%s]", description))
                .collect(Collectors.joining(","));

        return String.format("%s(%s)", getName(), parametersDescription);
    }
}

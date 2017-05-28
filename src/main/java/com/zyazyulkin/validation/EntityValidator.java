package com.zyazyulkin.validation;

import com.zyazyulkin.validation.registrar.ValidationAnnotationRegistrar;
import com.zyazyulkin.validation.verifier.ConstraintVerifier;
import com.zyazyulkin.validation.exception.InternalErrorException;
import com.zyazyulkin.validation.util.ExceptionUtil;
import com.zyazyulkin.validation.util.ObjectUtil;
import com.zyazyulkin.validation.violation.ConstraintViolation;
import com.zyazyulkin.validation.violation.ConstraintViolationExceptionSupplier;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EntityValidator {

    public static <E extends Throwable> void validate(
            Object entity, @NotNull ConstraintViolationExceptionSupplier<E> onConstraintViolation)
            throws E, InternalErrorException {

        validate(entity, onConstraintViolation, null);
    }

    public static <E1 extends Throwable, E2 extends Throwable> void validate(
            Object entity, @NotNull ConstraintViolationExceptionSupplier<E1> onConstraintViolation, Supplier<E2> onNull)
            throws E1, E2, InternalErrorException {

        List<ConstraintViolation> constraintViolations = getConstraintViolations(entity, onNull);

        if (!constraintViolations.isEmpty()) {
            E1 throwingException = onConstraintViolation.get(constraintViolations);

            if (throwingException != null) {
                throw throwingException;
            }
        }
    }

    public static boolean isValid(Object entity) throws InternalErrorException {
        return isValid(entity, false);
    }

    public static boolean isValid(Object entity, boolean onNull) throws InternalErrorException {
        return entity != null ? getConstraintViolations(entity).isEmpty() : onNull;
    }

    public static @NotNull List<ConstraintViolation> getConstraintViolations(Object entity)
            throws InternalErrorException {

        return getConstraintViolations(entity, null);
    }

    public static <E extends Throwable> @NotNull List<ConstraintViolation> getConstraintViolations(
            Object entity, Supplier<E> onNull)
            throws E, InternalErrorException {

        if (entity != null) {
            List<Field> entityFields = ObjectUtil.extractFields(entity);
            List<ConstraintViolation> constraintViolations = new ArrayList<>();

            for (Field entityField : entityFields) {
                List<ConstraintVerifier> constraintVerifiers = extractVerifiers(entityField);

                extractConstraintViolation(entity, entityField, constraintVerifiers)
                        .ifPresent(constraintViolations::add);
            }

            return constraintViolations;
        } else {
            ExceptionUtil.extractAndThrow(onNull);
            return new ArrayList<>();
        }
    }

    private static @NotNull List<ConstraintVerifier> extractVerifiers(@NotNull Field field)
            throws InternalErrorException {

        return Arrays.stream(field.getAnnotations())
                        .map(ValidationAnnotationRegistrar::createVerifier)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());
    }

    private static Optional<ConstraintViolation> extractConstraintViolation(
            @NotNull Object object, @NotNull Field field, @NotNull List<ConstraintVerifier> constraintVerifiers)
            throws InternalErrorException {

        try {
            field.setAccessible(true);
            Object fieldValue = field.get(object);

            List<ConstraintVerifier> failedVerifiers = constraintVerifiers.stream()
                    .filter(verifier -> !verifier.verify(fieldValue))
                    .collect(Collectors.toList());

            return !failedVerifiers.isEmpty() ?
                    Optional.of(new ConstraintViolation(field.getName(), failedVerifiers)) :
                    Optional.empty();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}

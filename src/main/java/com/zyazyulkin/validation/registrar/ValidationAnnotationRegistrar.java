package com.zyazyulkin.validation.registrar;

import com.zyazyulkin.validation.constraint.*;
import com.zyazyulkin.validation.constraint.EnumConstraint;
import com.zyazyulkin.validation.verifier.*;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ValidationAnnotationRegistrar {

    private static Map<Class<? extends Annotation>, ConstraintVerifierSupplier> registeredAnnotations = new HashMap<>();

    static {
        registerAnnotation(NotNullConstraint.class, annotation -> new NotNullVerifier());
        registerAnnotation(MinConstraint.class, annotation -> new MinVerifier(annotation.value()));
        registerAnnotation(MaxConstraint.class, annotation -> new MaxVerifier(annotation.value()));
        registerAnnotation(MinLengthConstraint.class, annotation -> new MinLengthVerifier(annotation.value()));
        registerAnnotation(MaxLengthConstraint.class, annotation -> new MaxLengthVerifier(annotation.value()));
        registerAnnotation(ValidConstraint.class, annotation -> new ValidVerifier());
        registerAnnotation(RegexConstraint.class, annotation -> new RegexVerifier(annotation.value()));
        registerAnnotation(EnumConstraint.class, annotation -> new EnumVerifier(annotation.value()));
        registerAnnotation(CharactersConstraint.class, annotation -> new CharactersVerifier(annotation.value()));
    }

    public static <A extends Annotation> void registerAnnotation(
            @NotNull Class<A> annotation,
            @NotNull ConstraintVerifierSupplier<A> constraintVerifierSupplier) {

        registeredAnnotations.put(annotation, constraintVerifierSupplier);
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> Optional<ConstraintVerifier> createVerifier(@NotNull A annotation) {
        ConstraintVerifierSupplier supplier = registeredAnnotations.get(annotation.annotationType());
        return supplier != null ? Optional.ofNullable(supplier.get(annotation)) : Optional.empty();
    }
}

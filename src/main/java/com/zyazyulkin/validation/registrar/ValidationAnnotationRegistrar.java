package com.zyazyulkin.validation.registrar;

import com.zyazyulkin.validation.constraint.*;
import com.zyazyulkin.validation.constraint.Enum;
import com.zyazyulkin.validation.verifier.*;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ValidationAnnotationRegistrar {

    private static Map<Class<? extends Annotation>, ConstraintVerifierSupplier> registeredAnnotations = new HashMap<>();

    static {
        registerAnnotation(NotNull.class, annotation -> new NotNullVerifier());
        registerAnnotation(Min.class, annotation -> new MinVerifier(annotation.value()));
        registerAnnotation(Max.class, annotation -> new MaxVerifier(annotation.value()));
        registerAnnotation(MinLength.class, annotation -> new MinLengthVerifier(annotation.value()));
        registerAnnotation(MaxLength.class, annotation -> new MaxLengthVerifier(annotation.value()));
        registerAnnotation(Valid.class, annotation -> new ValidVerifier());
        registerAnnotation(Regex.class, annotation -> new RegexVerifier(annotation.value()));
        registerAnnotation(Enum.class, annotation -> new EnumVerifier(annotation.value()));
        registerAnnotation(Characters.class, annotation -> new CharactersVerifier(annotation.value()));
    }

    public static <A extends Annotation> void registerAnnotation(
            @org.jetbrains.annotations.NotNull Class<A> annotation,
            @org.jetbrains.annotations.NotNull ConstraintVerifierSupplier<A> constraintVerifierSupplier) {

        registeredAnnotations.put(annotation, constraintVerifierSupplier);
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> Optional<ConstraintVerifier> createVerifier(
            @org.jetbrains.annotations.NotNull A annotation) {

        ConstraintVerifierSupplier supplier = registeredAnnotations.get(annotation.annotationType());
        return supplier != null ? Optional.ofNullable(supplier.get(annotation)) : Optional.empty();
    }
}

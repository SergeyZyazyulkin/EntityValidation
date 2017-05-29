package com.zyazyulkin.validation.registrar;

import com.zyazyulkin.validation.constraint.*;
import com.zyazyulkin.validation.constraint.ContainsEnum;
import com.zyazyulkin.validation.verifier.*;
import com.zyazyulkin.validation.verifier.impl.*;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ValidationAnnotationRegistrar {

    private static Map<Class<? extends Annotation>, ConstraintVerifierSupplier> registeredAnnotations = new HashMap<>();

    static {
        registerAnnotation(NotNull.class, a -> new NotNullVerifier(a.targets()));
        registerAnnotation(Min.class, a -> new MinVerifier(a.targets(), a.value()));
        registerAnnotation(Max.class, a -> new MaxVerifier(a.targets(), a.value()));
        registerAnnotation(MinLength.class, a -> new MinLengthVerifier(a.targets(), a.value()));
        registerAnnotation(MaxLength.class, a -> new MaxLengthVerifier(a.targets(), a.value()));
        registerAnnotation(Valid.class, a -> new ValidVerifier(a.targets()));
        registerAnnotation(Matches.class, a -> new MatchesVerifier(a.targets(), a.regex()));
        registerAnnotation(ContainsEnum.class, a -> new ContainsEnumVerifier(a.targets(), a.caseSensitive(), a.value()));
        registerAnnotation(ConsistsOf.class, a -> new ConsistsOfVerifier(a.targets(), a.characters()));
        registerAnnotation(ContainsDate.class, a -> new ContainsDateVerifier(a.targets(), a.format()));
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

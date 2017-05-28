package com.zyazyulkin.validation.test.util;

import com.zyazyulkin.validation.registrar.ValidationAnnotationRegistrar;
import com.zyazyulkin.validation.test.util.constraints.Invalid;
import com.zyazyulkin.validation.test.util.constraints.Invalid2;
import com.zyazyulkin.validation.test.util.constraints.Valid;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation;
import com.zyazyulkin.validation.test.util.verifier.AlwaysConstraintViolation2;
import com.zyazyulkin.validation.test.util.verifier.NeverConstraintViolation;
import org.junit.Before;

public class TestWithConstraints {

    @Before
    public void registerAnnotations() {
        ValidationAnnotationRegistrar.registerAnnotation(Valid.class, a -> new NeverConstraintViolation());
        ValidationAnnotationRegistrar.registerAnnotation(Invalid.class, a -> new AlwaysConstraintViolation());
        ValidationAnnotationRegistrar.registerAnnotation(Invalid2.class, a -> new AlwaysConstraintViolation2());
    }
}

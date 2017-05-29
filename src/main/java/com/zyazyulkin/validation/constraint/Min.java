package com.zyazyulkin.validation.constraint;

import com.zyazyulkin.validation.verifier.ConstraintTarget;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Min {

    double value();
    ConstraintTarget[] targets() default ConstraintTarget.FIELD;
}

package com.zyazyulkin.validation.constraint;

import com.zyazyulkin.validation.verifier.ConstraintTarget;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Valid {

    ConstraintTarget[] value() default ConstraintTarget.FIELD;
}

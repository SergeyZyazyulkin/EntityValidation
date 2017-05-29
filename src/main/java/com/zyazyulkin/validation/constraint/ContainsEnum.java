package com.zyazyulkin.validation.constraint;

import com.zyazyulkin.validation.verifier.ConstraintTarget;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContainsEnum {

    Class<? extends java.lang.Enum> value();
    boolean caseSensitive() default true;
    ConstraintTarget[] targets() default ConstraintTarget.FIELD;
}

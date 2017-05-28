package com.zyazyulkin.validation.constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinConstraint {

    double value();
}

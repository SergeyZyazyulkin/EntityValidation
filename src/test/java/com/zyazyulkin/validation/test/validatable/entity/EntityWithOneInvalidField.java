package com.zyazyulkin.validation.test.validatable.entity;

import com.zyazyulkin.validation.Validatable;
import com.zyazyulkin.validation.test.util.constraints.Invalid;

public class EntityWithOneInvalidField implements Validatable {

    @Invalid
    private String field;
}

package com.zyazyulkin.validation.test.entityvalidator.entity;

import com.zyazyulkin.validation.test.util.constraints.Invalid;

public class EntityWithOneInvalidField {

    @Invalid
    private String field;
}

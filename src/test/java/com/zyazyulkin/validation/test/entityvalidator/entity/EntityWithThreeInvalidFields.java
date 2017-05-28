package com.zyazyulkin.validation.test.entityvalidator.entity;

import com.zyazyulkin.validation.test.util.constraints.Invalid;
import com.zyazyulkin.validation.test.util.constraints.Invalid2;
import com.zyazyulkin.validation.test.util.constraints.Valid;

public class EntityWithThreeInvalidFields {

    @Invalid
    private String field1;

    @Valid
    private Integer field2;

    @Invalid2
    private Double field3;

    @Invalid
    @Invalid2
    private Long field4;
}

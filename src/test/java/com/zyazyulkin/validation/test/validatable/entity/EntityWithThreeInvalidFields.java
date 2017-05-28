package com.zyazyulkin.validation.test.validatable.entity;

import com.zyazyulkin.validation.Validatable;
import com.zyazyulkin.validation.test.util.constraints.Invalid;
import com.zyazyulkin.validation.test.util.constraints.Invalid2;
import com.zyazyulkin.validation.test.util.constraints.Valid;

public class EntityWithThreeInvalidFields implements Validatable {

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

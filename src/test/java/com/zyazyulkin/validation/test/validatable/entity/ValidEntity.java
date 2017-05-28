package com.zyazyulkin.validation.test.validatable.entity;

import com.zyazyulkin.validation.Validatable;
import com.zyazyulkin.validation.test.util.constraints.Valid;

public class ValidEntity implements Validatable {

    @Valid
    private String field;
}

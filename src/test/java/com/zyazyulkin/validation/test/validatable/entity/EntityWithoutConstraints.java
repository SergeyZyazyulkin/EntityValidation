package com.zyazyulkin.validation.test.validatable.entity;

import com.zyazyulkin.validation.Validatable;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithoutConstraints implements Validatable {

    private String field1;
}

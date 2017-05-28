package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.*;
import com.zyazyulkin.validation.constraint.Enum;
import com.zyazyulkin.validation.test.util.constraints.Valid;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithConstraints {

    @NotNull
    @MinLength(1)
    @MaxLength(3)
    @Regex("a*")
    private String field1;

    @Min(-1)
    @Max(1)
    private int field2;

    @Valid
    private EntityWithNotNull field3;

    @Enum(TestEnum.class)
    private String field4;

    @Characters("123")
    private String field5;

    public EntityWithConstraints(String field1, int field2, EntityWithNotNull field3, String field4, String field5) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }
}

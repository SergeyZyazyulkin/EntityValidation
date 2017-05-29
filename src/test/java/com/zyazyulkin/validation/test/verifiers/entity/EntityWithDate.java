package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.Date;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithDate {

    @Date("dd.MM.yyyy")
    private String date;

    public EntityWithDate(String date) {
        this.date = date;
    }
}

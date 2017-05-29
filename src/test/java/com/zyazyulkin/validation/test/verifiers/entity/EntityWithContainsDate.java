package com.zyazyulkin.validation.test.verifiers.entity;

import com.zyazyulkin.validation.constraint.ContainsDate;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;

@SuppressWarnings
public class EntityWithContainsDate {

    @ContainsDate(format = "dd.MM.yyyy")
    private String date;

    public EntityWithContainsDate(String date) {
        this.date = date;
    }
}

package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.exception.InvalidConstraintException;
import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ContainsDateVerifier extends AbstractConstraintVerifier {

    private String pattern;

    public ContainsDateVerifier(@NotNull ConstraintTarget[] constraintTargets, String pattern) {
        super(constraintTargets);
        this.pattern = pattern;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, String.class, this::verifyValue);
    }

    public boolean verifyValue(String value) {
        try {
            new SimpleDateFormat(pattern).parse(value);
            return true;
        } catch (IllegalArgumentException e) {
            throw InvalidConstraintException.invalidParameter("pattern", pattern);
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    protected String[] getParametersDescription() {
        return new String[] {pattern};
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ContainsDateVerifier that = (ContainsDateVerifier) o;

        return pattern != null ? pattern.equals(that.pattern) : that.pattern == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pattern != null ? pattern.hashCode() : 0);
        return result;
    }
}

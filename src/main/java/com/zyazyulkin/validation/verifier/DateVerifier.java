package com.zyazyulkin.validation.verifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateVerifier extends AbstractConstraintVerifier {

    private String pattern;

    public DateVerifier(String pattern) {
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
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    protected String getParametersForToString() {
        return pattern;
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

        DateVerifier that = (DateVerifier) o;

        return pattern != null ? pattern.equals(that.pattern) : that.pattern == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pattern != null ? pattern.hashCode() : 0);
        return result;
    }
}

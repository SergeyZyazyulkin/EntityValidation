package com.zyazyulkin.validation.verifier;

import com.zyazyulkin.validation.exception.InvalidConstraintException;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexVerifier extends AbstractConstraintVerifier {

    private String regex;

    public RegexVerifier(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean verify(Object value) throws InvalidConstraintException {
        return verify(value, CharSequence.class, this::verifyValue);
    }

    private boolean verifyValue(CharSequence charSequence) throws InvalidConstraintException {
        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(charSequence).matches();
        } catch (PatternSyntaxException e) {
            throw InvalidConstraintException.invalidParameter("regex", regex);
        }
    }

    @Override
    protected String getParametersForToString() {
        return regex;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegexVerifier that = (RegexVerifier) o;

        return regex != null ? regex.equals(that.regex) : that.regex == null;
    }

    @Override
    public int hashCode() {
        return regex != null ? regex.hashCode() : 0;
    }
}

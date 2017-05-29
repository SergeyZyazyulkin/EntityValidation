package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.exception.InvalidConstraintException;
import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class MatchesVerifier extends AbstractConstraintVerifier {

    private String regex;

    public MatchesVerifier(@NotNull ConstraintTarget[] constraintTargets, String regex) {
        super(constraintTargets);
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
    protected String[] getParametersDescription() {
        return new String[] {regex};
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

        MatchesVerifier that = (MatchesVerifier) o;

        return regex != null ? regex.equals(that.regex) : that.regex == null;
    }

    @Override
    public int hashCode() {
        return regex != null ? regex.hashCode() : 0;
    }
}

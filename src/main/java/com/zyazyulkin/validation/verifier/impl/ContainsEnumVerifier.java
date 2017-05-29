package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContainsEnumVerifier extends AbstractConstraintVerifier {

    private boolean caseSensitive;
    private Class<? extends Enum> enumClass;

    public ContainsEnumVerifier(
            @NotNull ConstraintTarget[] constraintTargets, boolean caseSensitive, Class<? extends Enum> enumClass) {

        super(constraintTargets);
        this.caseSensitive = caseSensitive;
        this.enumClass = enumClass;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, String.class, this::verifyValue);
    }

    @SuppressWarnings("unchecked")
    private boolean verifyValue(String string) {
        try {
            Enum.valueOf(enumClass, caseSensitive ? string : string.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    protected String[] getParametersDescription() {
        String caseSensitiveDescription = caseSensitive ? "case sensitive" : "case insensitive";

        String enumConstantsDescription = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(","));

        return new String[] {caseSensitiveDescription, enumConstantsDescription};
    }

    public Class<? extends Enum> getEnumClass() {
        return enumClass;
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

        ContainsEnumVerifier that = (ContainsEnumVerifier) o;

        return enumClass != null ? enumClass.equals(that.enumClass) : that.enumClass == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enumClass != null ? enumClass.hashCode() : 0);
        return result;
    }
}

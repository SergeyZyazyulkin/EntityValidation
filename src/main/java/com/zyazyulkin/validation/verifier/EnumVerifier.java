package com.zyazyulkin.validation.verifier;

import java.util.Arrays;

public class EnumVerifier extends AbstractConstraintVerifier {

    private Class<? extends Enum> enumClass;

    public EnumVerifier(Class<? extends Enum> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, String.class, this::verifyValue);
    }

    @SuppressWarnings("unchecked")
    private boolean verifyValue(String string) {
        try {
            Enum.valueOf(enumClass, string.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    protected String getParametersForToString() {
        return Arrays.toString(enumClass.getEnumConstants());
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

        EnumVerifier that = (EnumVerifier) o;

        return enumClass != null ? enumClass.equals(that.enumClass) : that.enumClass == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enumClass != null ? enumClass.hashCode() : 0);
        return result;
    }
}

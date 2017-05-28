package com.zyazyulkin.validation.verifier;

public class MaxLengthVerifier extends AbstractConstraintVerifier {

    private int maxLength;

    public MaxLengthVerifier(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, CharSequence.class, charSequence -> charSequence.length() <= maxLength);
    }

    @Override
    public String getParametersForToString() {
        return Integer.toString(maxLength);
    }

    public int getMaxLength() {
        return maxLength;
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

        MaxLengthVerifier that = (MaxLengthVerifier) o;

        return maxLength == that.maxLength;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + maxLength;
        return result;
    }
}

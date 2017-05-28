package com.zyazyulkin.validation.verifier;

public class MinLengthVerifier extends AbstractConstraintVerifier {

    private int minLength;

    public MinLengthVerifier(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, CharSequence.class, charSequence -> charSequence.length() >= minLength);
    }

    @Override
    protected String getParametersForToString() {
        return Integer.toString(minLength);
    }

    public int getMinLength() {
        return minLength;
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

        MinLengthVerifier that = (MinLengthVerifier) o;

        return minLength == that.minLength;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + minLength;
        return result;
    }
}

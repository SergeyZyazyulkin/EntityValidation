package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

public class MaxVerifier extends AbstractConstraintVerifier {

    private double max;

    public MaxVerifier(@NotNull ConstraintTarget[] constraintTargets, double max) {
        super(constraintTargets);
        this.max = max;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Number.class, number -> number.doubleValue() <= max);
    }

    @Override
    protected String[] getParametersDescription() {
        return new String[] {Double.toString(max)};
    }

    public double getMax() {
        return max;
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

        MaxVerifier that = (MaxVerifier) o;

        return Double.compare(that.max, max) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(max);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

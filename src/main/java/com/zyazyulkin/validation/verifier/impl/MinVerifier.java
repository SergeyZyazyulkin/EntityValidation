package com.zyazyulkin.validation.verifier.impl;

import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

public class MinVerifier extends AbstractConstraintVerifier {

    private double min;

    public MinVerifier(@NotNull ConstraintTarget[] constraintTargets, double min) {
        super(constraintTargets);
        this.min = min;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, Number.class, number -> number.doubleValue() >= min);
    }

    @Override
    protected String[] getParametersDescription() {
        return new String[] {Double.toString(min)};
    }

    public double getMin() {
        return min;
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

        MinVerifier that = (MinVerifier) o;

        return Double.compare(that.min, min) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(min);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

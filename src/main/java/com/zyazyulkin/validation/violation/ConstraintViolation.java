package com.zyazyulkin.validation.violation;

import com.zyazyulkin.validation.verifier.ConstraintVerifier;
import com.zyazyulkin.validation.util.Comparator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConstraintViolation {

    @NotNull private String field;
    @NotNull private List<ConstraintVerifier> verifiers;

    public ConstraintViolation(@NotNull String field, @NotNull List<ConstraintVerifier> verifiers) {
        this.field = field;
        this.verifiers = new ArrayList<>(verifiers);
    }

    public @NotNull String getField() {
        return field;
    }

    public @NotNull List<ConstraintVerifier> getVerifiers() {
        return new ArrayList<>(verifiers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            ConstraintViolation that = (ConstraintViolation) o;
            return Objects.equals(field, that.field) && Comparator.isCollectionsEqual(verifiers, that.verifiers);
        }
    }

    @Override
    public int hashCode() {
        return 31 * field.hashCode() + verifiers.hashCode();
    }
}

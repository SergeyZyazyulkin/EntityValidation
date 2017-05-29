package com.zyazyulkin.validation.verifier.impl;

import com.google.common.base.CharMatcher;
import com.zyazyulkin.validation.verifier.AbstractConstraintVerifier;
import com.zyazyulkin.validation.verifier.ConstraintTarget;
import org.jetbrains.annotations.NotNull;

public class ConsistsOfVerifier extends AbstractConstraintVerifier {

    private String characters;

    public ConsistsOfVerifier(@NotNull ConstraintTarget[] constraintTargets, String characters) {
        super(constraintTargets);
        this.characters = characters;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, CharSequence.class,
                charSequence -> CharMatcher.anyOf(characters).matchesAllOf(charSequence));
    }

    @Override
    protected String[] getParametersDescription() {
        return new String[] {characters};
    }

    public String getCharacters() {
        return characters;
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

        ConsistsOfVerifier that = (ConsistsOfVerifier) o;

        return characters != null ? characters.equals(that.characters) : that.characters == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (characters != null ? characters.hashCode() : 0);
        return result;
    }
}

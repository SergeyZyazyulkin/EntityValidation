package com.zyazyulkin.validation.verifier;

import com.google.common.base.CharMatcher;

public class CharactersVerifier extends AbstractConstraintVerifier {

    private String characters;

    public CharactersVerifier(String characters) {
        this.characters = characters;
    }

    @Override
    public boolean verify(Object value) {
        return verify(value, CharSequence.class,
                charSequence -> CharMatcher.anyOf(characters).matchesAllOf(charSequence));
    }

    @Override
    protected String getParametersForToString() {
        return characters;
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

        CharactersVerifier that = (CharactersVerifier) o;

        return characters != null ? characters.equals(that.characters) : that.characters == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (characters != null ? characters.hashCode() : 0);
        return result;
    }
}

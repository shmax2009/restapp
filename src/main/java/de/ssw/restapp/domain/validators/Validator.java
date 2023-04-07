package de.ssw.restapp.domain.validators;

public interface Validator<T> {
    String validate(T object);
}

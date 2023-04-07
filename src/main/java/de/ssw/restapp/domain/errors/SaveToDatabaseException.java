package de.ssw.restapp.domain.errors;

public class SaveToDatabaseException extends RuntimeException {

    public SaveToDatabaseException(String objectType, String message) {
        super("Could not save " + objectType + " to database: " + message);
    }
}

package de.ssw.restapp.domain.errors;

public class DeleteFromDatabaseException extends RuntimeException {

    public DeleteFromDatabaseException(String objectType, String message) {
        super("Could not delete " + objectType + " from database: " + message);
    }
}

package de.ssw.restapp.domain.errors;

public class GetFromDatabaseException extends RuntimeException {

    public GetFromDatabaseException(String objectType, String message) {
        super("Could not get " + objectType + " from database: " + message);
    }
}

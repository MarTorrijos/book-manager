package org.example.bookmanager.exceptions;

public class InvalidBookException extends RuntimeException {

    public InvalidBookException(String message) {
        super(message);
    }

}
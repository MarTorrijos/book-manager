package org.example.bookmanager.exceptions;

public class AuthorNotFound extends RuntimeException {

    public AuthorNotFound(String message) {
        super(message);
    }

}
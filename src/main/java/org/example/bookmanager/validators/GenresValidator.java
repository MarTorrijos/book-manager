package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class GenresValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getGenres() == null || book.getGenres().isEmpty()) {
            throw new InvalidBookException("Genres can't be empty");
        }
    }

}
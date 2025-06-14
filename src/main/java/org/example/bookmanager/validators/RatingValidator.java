package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class RatingValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getRating() != null && (book.getRating() < 0 || book.getRating() > 5)) {
            throw new InvalidBookException("Rating must be between 0 and 5");
        }
    }

}
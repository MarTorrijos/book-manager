package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class ReviewValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getReview() != null && book.getReview().length() > 1000) {
            throw new InvalidBookException("Review can't exceed 1000 characters");
        }
    }

}
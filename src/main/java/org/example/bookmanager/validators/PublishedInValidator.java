package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

import java.time.Year;

public class PublishedInValidator implements BookValidator {

    private int currentYear = Year.now().getValue();

    @Override
    public void validate(Book book) {
        if (book.getPublishedIn() != null && (book.getPublishedIn() < 0 || book.getPublishedIn() > currentYear)) {
            throw new InvalidBookException("Published year must be a valid year (between 0 and " + currentYear + ")");
        }
    }

}
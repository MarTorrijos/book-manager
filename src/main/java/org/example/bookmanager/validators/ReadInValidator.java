package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

import java.time.Year;

public class ReadInValidator implements BookValidator {

    private int currentYear = Year.now().getValue();

    @Override
    public void validate(Book book) {
        if (book.getReadIn() != null && (book.getReadIn() < 0 || book.getReadIn() > currentYear)) {
            throw new InvalidBookException("Read-in year must be a valid year (between 0 and " + currentYear + ")");
        }
    }

}
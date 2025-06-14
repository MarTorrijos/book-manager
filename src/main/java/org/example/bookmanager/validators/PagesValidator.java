package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class PagesValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getPages() == null || book.getPages() <= 0) {
            throw new InvalidBookException("Pages must be a positive number");
        }
    }

}
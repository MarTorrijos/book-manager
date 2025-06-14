package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class TitleValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidBookException("Book title can't be empty");
        }
        if (book.getTitle().length() > 200) {
            throw new InvalidBookException("Book title can't exceed 200 characters");
        }
    }

}
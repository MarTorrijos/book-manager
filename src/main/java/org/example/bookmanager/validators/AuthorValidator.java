package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class AuthorValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new InvalidBookException("Author can't be empty");
        }
        if (book.getAuthor().length() > 200) {
            throw new InvalidBookException("Author can't exceed 200 characters");
        }
    }

}
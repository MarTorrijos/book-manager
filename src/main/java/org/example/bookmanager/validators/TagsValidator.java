package org.example.bookmanager.validators;

import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;

public class TagsValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getTags() != null && book.getTags().size() > 20) {
            throw new InvalidBookException("A book can have a maximum of 20 tags");
        }
    }

}
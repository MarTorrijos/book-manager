package org.example.bookmanager.service;

import org.bson.types.ObjectId;
import org.example.bookmanager.exceptions.AuthorNotFound;
import org.example.bookmanager.exceptions.BookNotFoundException;
import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.function.Consumer;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        validateBook(book);

        Book existingBook = bookRepository.findByTitle(book.getTitle());
        if (existingBook != null) {
            throw new InvalidBookException("Book with title \"" + book.getTitle() + "\" already exists");
        }

        return bookRepository.save(book);
    }

    public Book getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Book with title \"" + title + "\" not found");
        }
        return book;
    }

    public Book getBookById(ObjectId id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id \"" + id + "\" not found"));
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        if (books.isEmpty()) {
            throw new AuthorNotFound("No books found for author \"" + author + "\"");
        }
        return books;
    }

    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId()).orElseThrow(() ->
                new BookNotFoundException("Book not found with id \"" + book.getId() + "\""));

        updateField(book.getTitle(), existingBook::setTitle);
        updateField(book.getAuthor(), existingBook::setAuthor);
        updateField(book.getPublishedIn(), existingBook::setPublishedIn);
        updateField(book.getGenres(), existingBook::setGenres);
        updateField(book.getPages(), existingBook::setPages);
        updateField(book.getStatus(), existingBook::setStatus);
        updateField(book.getReadIn(), existingBook::setReadIn);
        updateField(book.getRating(), existingBook::setRating);
        updateField(book.getReview(), existingBook::setReview);
        updateField(book.getTags(), existingBook::setTags);

        return bookRepository.save(existingBook);
    }

    public void deleteBookById(ObjectId id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id \"" + id + "\" not found"));

        bookRepository.delete(existingBook);
    }

    public void deleteBookByTitle(String title) {
        Book existingBook = bookRepository.findByTitle(title);
        if (existingBook == null) {
            throw new BookNotFoundException("Book with title \"" + title + "\" not found");
        }

        bookRepository.delete(existingBook);
    }


    private <T> void updateField(T newValue, Consumer<T> updateMethod) {
        if (newValue != null) {
            updateMethod.accept(newValue);
        }
    }

    private void validateBook(Book book) {
        int currentYear = Year.now().getValue();

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidBookException("Book title can't be empty");
        }
        if (book.getTitle().length() > 200) {
            throw new InvalidBookException("Book title can't exceed 200 characters");
        }

        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new InvalidBookException("Author can't be empty");
        }
        if (book.getAuthor().length() > 200) {
            throw new InvalidBookException("Author can't exceed 200 characters");
        }

        if (book.getPublishedIn() != null && (book.getPublishedIn() < 0 || book.getPublishedIn() > currentYear)) {
            throw new InvalidBookException("Published year must be a valid year (between 0 and " + currentYear + ")");
        }

        if (book.getGenres() == null || book.getGenres().isEmpty()) {
            throw new InvalidBookException("Genres can't be empty");
        }

        if (book.getPages() == null || book.getPages() <= 0) {
            throw new InvalidBookException("Pages must be a positive number");
        }

        if (book.getReadIn() != null && (book.getReadIn() < 0 || book.getReadIn() > currentYear)) {
            throw new InvalidBookException("Read-in year must be a valid year (between 0 and " + currentYear + ")");
        }

        if (book.getRating() != null && (book.getRating() < 0 || book.getRating() > 5)) {
            throw new InvalidBookException("Rating must be between 0 and 5");
        }

        if (book.getReview() != null && book.getReview().length() > 1000) {
            throw new InvalidBookException("Review can't exceed 1000 characters");
        }

        if (book.getTags() != null && book.getTags().size() > 20) {
            throw new InvalidBookException("A book can have a maximum of 20 tags");
        }
    }

}
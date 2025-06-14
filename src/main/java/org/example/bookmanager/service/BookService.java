package org.example.bookmanager.service;

import org.bson.types.ObjectId;
import org.example.bookmanager.exceptions.AuthorNotFound;
import org.example.bookmanager.exceptions.BookNotFoundException;
import org.example.bookmanager.exceptions.InvalidBookException;
import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.example.bookmanager.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class BookService {

    private BookRepository bookRepository;
    private List<BookValidator> validators;

    @Autowired
    public BookService(BookRepository bookRepository, List<BookValidator> validators) {
        this.bookRepository = bookRepository;
        this.validators = validators;
    }

    public Book addBook(Book book) {

        validators.forEach(validator -> validator.validate(book));

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

}
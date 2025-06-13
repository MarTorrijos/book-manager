package org.example.bookmanager.service;

import org.bson.types.ObjectId;
import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }

    public Optional<Book> getBookById(String id) {
        ObjectId objectId = new ObjectId(id);
        return bookRepository.findById(objectId);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

}
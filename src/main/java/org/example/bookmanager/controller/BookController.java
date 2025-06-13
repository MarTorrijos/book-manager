package org.example.bookmanager.controller;

import org.example.bookmanager.model.Book;
import org.example.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    @GetMapping("/name")
    public ResponseEntity<Book> getBookByName(@RequestParam String name) {
        Book book = bookService.getBookByName(name);
        return book == null
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(book);
    }

    // getBookById

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    // updateBook


    // deleteBook

}
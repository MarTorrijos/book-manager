package org.example.bookmanager.controller;

import org.bson.types.ObjectId;
import org.example.bookmanager.exceptions.AuthorNotFound;
import org.example.bookmanager.exceptions.BookNotAddedException;
import org.example.bookmanager.exceptions.BookNotFoundException;
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
        try {
            Book addedBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
        } catch (BookNotAddedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        try {
            Book book = bookService.getBookByTitle(title);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable ObjectId id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        try {
            List<Book> books = bookService.getBooksByAuthor(author);
            return ResponseEntity.ok(books);
        } catch (AuthorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable ObjectId id, @RequestBody Book book) {
        try {
            book.setId(id);
            Book updatedBook = bookService.updateBook(book);
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestParam(value = "id", required = false) ObjectId id,
                                           @RequestParam(value = "title", required = false) String title) {
        try {
            if (id != null) {
                bookService.deleteBookById(id);
            } else if (title != null && !title.trim().isEmpty()) {
                bookService.deleteBookByTitle(title);
            } else {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
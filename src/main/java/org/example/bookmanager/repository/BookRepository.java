package org.example.bookmanager.repository;

import org.bson.types.ObjectId;
import org.example.bookmanager.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, ObjectId> {

    Book findByTitle(String title);
    List<Book> findByAuthor(String author);

}
package org.example.bookmanager.repository;

import org.bson.types.ObjectId;
import org.example.bookmanager.model.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, ObjectId> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    Book findByTitle(String title);

    @Query("{ 'author': { $regex: ?0, $options: 'i' } }")
    List<Book> findByAuthor(String author);

}
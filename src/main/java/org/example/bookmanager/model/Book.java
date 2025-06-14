package org.example.bookmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class Book {

    @Id
    private ObjectId id;
    private String title;
    private String author;
    private Integer publishedIn;
    private List<String> genres;
    private Integer pages;
    private boolean status;
    private Integer readIn;
    private Float rating;
    private String review;
    private List<String> tags;

    public Book() { }

    public Book(String title, String author, int publishedIn, List<String> genres, int pages, boolean status, int readIn,
                float rating, String review, List<String> tags) {
        this.title = title;
        this.author = author;
        this.publishedIn = publishedIn;
        this.genres = genres;
        this.pages = pages;
        this.status = status;
        this.readIn = readIn;
        this.rating = rating;
        this.review = review;
        this.tags = tags;
    }

    // Lombok is not creating this getter for some reason
    public boolean getStatus() {
        return status;
    }

}
package org.example.bookmanager.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedIn() {
        return publishedIn;
    }

    public void setPublishedIn(Integer publishedIn) {
        this.publishedIn = publishedIn;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getReadIn() {
        return readIn;
    }

    public void setReadIn(Integer readIn) {
        this.readIn = readIn;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
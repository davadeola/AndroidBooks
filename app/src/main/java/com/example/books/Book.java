package com.example.books;

public class Book {
    public String id;
    public String title;
    public String subTitle;
    public String[] authors;
    public String publisher;
    public String publishedDate;

    public Book(String id, String title, String subTitle, String[] authors, String publisher, String publishedDate){
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public String getId() {
        return id;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }


}

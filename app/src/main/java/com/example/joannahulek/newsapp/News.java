package com.example.joannahulek.newsapp;

/**
 * Created by Joasia on 10.07.2017.
 */

public class News {

    private final String title;
    private final String description;
    private final String author;
    private final String publishedAt;
    private final String url;

    public News(String title, String author, String description, String publishedAt, String url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public final String getTitle() {
        return title;
    }

    public final String getDescription() {
        return description;
    }

    public final String getAuthor() {
        return author;
    }

    public final String getPublishedAt() {
        if (publishedAt != null) {
            return publishedAt.replace("T", " ").replace("Z", "");
        } else return publishedAt;
    }

    public final String getUrl() {
        return url;
    }
}

package com.example.joannahulek.newsapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Joasia on 10.07.2017.
 */

public class ViewHolder {
    private final TextView title;
    private final TextView description;
    private final TextView author;
    private final TextView publishedAt;
    private final View url;

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getAuthor() {
        return author;
    }

    public TextView getPublishedAt() {
        return publishedAt;
    }

    public View getUrl() {
        return url;
    }

    public ViewHolder(TextView title, TextView description, TextView author, TextView publishedAt, View url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publishedAt = publishedAt;
        this.url = url;

    }
}
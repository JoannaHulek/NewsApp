package com.example.joannahulek.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Joasia on 10.07.2017.
 */

public class NewsTaskLoader extends AsyncTaskLoader<List<News>> {
    private final String url;
    private final QueryUtils<List<News>> newsQuery = new QueryUtils<>(new NewsQueryConverter());

    public NewsTaskLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (url == null) {
            return null;
        }
        return newsQuery.fetchData(url);
    }
}

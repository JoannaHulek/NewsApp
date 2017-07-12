package com.example.joannahulek.newsapp;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

/**
 * Created by Joasia on 10.07.2017.
 */

public class NewsCallbackLoader implements LoaderManager.LoaderCallbacks<List<News>> {

    public final static int LOADER_ID = 1;
    private final AppCompatActivity activity;
    private final NewsAdapter adapter;
    private static final String URL =
            "http://content.guardianapis.com/search?q=Poland&api-key=d2b369bc-0532-4994-81d9-c1c0513e19dc";

    public NewsCallbackLoader(AppCompatActivity context, NewsAdapter adapter) {
        this.activity = context;
        this.adapter = adapter;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsTaskLoader(activity, URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        activity.findViewById(R.id.loading_indicator).setVisibility(View.GONE);
        ((SwipeRefreshLayout) activity.findViewById(R.id.swiperefresh)).setRefreshing(false);
        adapter.clear();
        if (data != null && !data.isEmpty()) {
            activity.findViewById(R.id.empty_view).setVisibility(View.GONE);
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}

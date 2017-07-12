package com.example.joannahulek.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NEWS_LOADER_ID = 1;
    public static final String ADAPTER_KEY = "adapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkInternetConnection()) {
            fillListView();
        }
    }

    private void loadData(NewsAdapter adapter) {
        LoaderManager manager = getSupportLoaderManager();
        manager.restartLoader(NewsCallbackLoader.LOADER_ID, null, new NewsCallbackLoader(this, adapter));
    }

    private void fillListView() {
        ListView newsListView = (ListView) findViewById(R.id.news_list_view);
        final NewsAdapter adapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(adapter);
        loadData(adapter);

        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(adapter);
            }
        });
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyStateTextView.setText(R.string.no_news_found);
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            return true;
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            return false;
        }
    }
}




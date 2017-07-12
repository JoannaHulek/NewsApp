package com.example.joannahulek.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joasia on 10.07.2017.
 */

class NewsAdapter extends ArrayAdapter<News> {

    private int selectedPos = -1;

    public NewsAdapter(Context context, ArrayList<News> items) {
        super(context, 0, items);
    }

    public void setSelectedPosition(int pos) {
        selectedPos = pos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);

            holder = new ViewHolder(
                    (TextView) v.findViewById(R.id.title_text_view),
                    (TextView) v.findViewById(R.id.description_text_view),
                    (TextView) v.findViewById(R.id.author_text_view),
                    (TextView) v.findViewById(R.id.publishedAt_text_view),
                    v.findViewById(R.id.news_layout));

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        final News newsItem = getItem(position);
        if (newsItem != null) {
            setTextOrHide(holder.getTitle(), newsItem.getTitle());
            setTextOrHide(holder.getDescription(), newsItem.getDescription());
            setTextOrHide(holder.getAuthor(), newsItem.getAuthor());
            setTextOrHide(holder.getPublishedAt(), newsItem.getPublishedAt());
            if (holder.getUrl() != null) {
                holder.getUrl().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri newsLink = Uri.parse(newsItem.getUrl());
                        Intent i = new Intent(Intent.ACTION_VIEW, newsLink);
                        getContext().startActivity(i);
                    }
                });
            }
        }
        return v;
    }

    private void setTextOrHide(TextView view, String text) {
        if (text != null) {
            view.setText(text);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}

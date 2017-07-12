package com.example.joannahulek.newsapp;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joasia on 10.07.2017.
 */

class NewsQueryConverter implements QueryConverter<java.util.List<News>> {
    @Override
    public List<News> convert(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<News> newses = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(json).getJSONObject("response");

            if (baseJsonResponse.has("results")) {
                JSONArray newsArray = baseJsonResponse.getJSONArray("results");
                for (int i = 0; i < newsArray.length(); i++) {
                    JSONObject currentNews = newsArray.getJSONObject(i);

                    String title = getOrNothing(currentNews, "webTitle");
                    String description = getOrNothing(currentNews, "sectionName");
                    String author = getOrNothing(currentNews, "contributor");
                    String publishedAt = getOrNothing(currentNews, "webPublicationDate");
                    String url = getOrNothing(currentNews, "webUrl");

                    News news = new News(title, description, author, publishedAt, url);
                    newses.add(news);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newses;
    }

    private static String getOrNothing(JSONObject object, String var) throws JSONException {
        if (object.has(var)) {
            return object.getString(var);
        } else
            return null;
    }
}

package com.example.joannahulek.newsapp;

/**
 * Created by Joasia on 10.07.2017.
 */

interface QueryConverter<T> {
    T convert(String json);
}

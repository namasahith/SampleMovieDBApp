package com.example.moviedb.Listeners;

import com.example.moviedb.Models.SearchResponse;

public interface OnSearchListener {
    void onResponse(SearchResponse response);
    void onError(String message);
}

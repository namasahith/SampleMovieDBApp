package com.example.moviedb.Listeners;

import com.example.moviedb.Models.MovieDetailResponse;

public interface OnMovieDetailListener {
    void onResponse(MovieDetailResponse response);
    void onError(String message);
}

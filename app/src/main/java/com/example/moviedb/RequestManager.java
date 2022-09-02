package com.example.moviedb;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.moviedb.Listeners.OnMovieDetailListener;
import com.example.moviedb.Listeners.OnSearchListener;
import com.example.moviedb.Models.MovieDetailResponse;
import com.example.moviedb.Models.SearchResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    public RequestManager(Context context) {
        this.context = context;
    }

    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://movie-database-alternative.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void searchMovie(OnSearchListener listener, String movie_name){
        getMovies getMovies=retrofit.create(RequestManager.getMovies.class);
        Call<SearchResponse> call=getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d("URL", call.request().url().toString());
                if(!response.isSuccessful()){
                    try {
                        Log.d("ERROR RESPONSE", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "No Response Received", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void searchMovieDetails(OnMovieDetailListener listener, String id){
        getMovieDetails getMovieDetails=retrofit.create(RequestManager.getMovieDetails.class);
        Call<MovieDetailResponse> call=getMovieDetails.callMovieDetails(id);

        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                Log.d("URL", call.request().url().toString());
                if(!response.isSuccessful()){
                    try {
                        Log.d("ERROR RESPONSE", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "No Response Received", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies{
        @Headers({
                "Accept: application/json",
                "X-RapidAPI-Host: movie-database-alternative.p.rapidapi.com",
                "X-RapidAPI-Key: 7edf7ce695msh3fc1ca3b2705186p128096jsn93a426bf43fd"
        })
        @GET("/")
        Call<SearchResponse> callMovies(
                @Query(value = "s") String movie_name
        );
    }

    public interface getMovieDetails{
        @Headers({
                "Accept: application/json",
                "X-RapidAPI-Host: movie-database-alternative.p.rapidapi.com",
                "X-RapidAPI-Key: 7edf7ce695msh3fc1ca3b2705186p128096jsn93a426bf43fd"
        })
        @GET("/")
        Call<MovieDetailResponse> callMovieDetails(
                @Query(value = "i") String id
        );
    }

}

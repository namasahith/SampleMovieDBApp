package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviedb.Adapters.RatingsAdapter;
import com.example.moviedb.Listeners.OnMovieDetailListener;
import com.example.moviedb.Models.MovieDetailResponse;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    TextView movie_detail_plot,movie_detail_name,movie_detail_released,movie_detail_runtime,movie_detail_actors;
    ImageView movie_detail_poster;
    RecyclerView recycler_ratings;
    RatingsAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movie_detail_name=findViewById(R.id.movie_detail_name);
        movie_detail_plot=findViewById(R.id.movie_detail_plot);
        movie_detail_released=findViewById(R.id.movie_detail_released);
        movie_detail_runtime=findViewById(R.id.movie_detail_runtime);
        movie_detail_actors=findViewById(R.id.movie_detail_actors);
        movie_detail_poster=findViewById(R.id.movie_detail_poster);
        recycler_ratings=findViewById(R.id.recycler_ratings);

        manager=new RequestManager(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait...");
        dialog.show();
        String id=getIntent().getStringExtra("data");
        manager.searchMovieDetails(listener,id);
    }

    private OnMovieDetailListener listener= new OnMovieDetailListener() {
        @Override
        public void onResponse(MovieDetailResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(MovieDetails.this, "No Details found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MovieDetails.this, "ERROR!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(MovieDetailResponse response) {
        movie_detail_name.setText(response.getTitle());
        movie_detail_runtime.setText("Movie Runtime : "+response.getRuntime());
        movie_detail_released.setText("Released on : "+response.getReleased());
        movie_detail_actors.setText(response.getActors());
        movie_detail_plot.setText(response.getPlot());
        try {
            Picasso.get().load(response.getPoster()).into(movie_detail_poster);
        }catch (Exception e){
            e.printStackTrace();
        }
        recycler_ratings.setHasFixedSize(true);
        recycler_ratings.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new RatingsAdapter(this,response.getRatings());
        recycler_ratings.setAdapter(adapter);
    }
}
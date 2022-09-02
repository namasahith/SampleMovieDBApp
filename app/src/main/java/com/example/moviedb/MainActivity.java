package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.moviedb.Adapters.HomeRecyclerAdapter;
import com.example.moviedb.Listeners.OnMovieClickListener;
import com.example.moviedb.Listeners.OnSearchListener;
import com.example.moviedb.Models.SearchResponse;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {
    SearchView search_view;
    RecyclerView recycler_view;
    HomeRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view=findViewById(R.id.search_view);
        recycler_view=findViewById(R.id.recycler_view_home);
        dialog =  new ProgressDialog(this);
        manager = new RequestManager(this);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Please Wait...");
                dialog.show();
                manager.searchMovie(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnSearchListener listener = new OnSearchListener() {
        @Override
        public void onResponse(SearchResponse response) {
            dialog.dismiss();
            if(response==null){
                Toast.makeText(MainActivity.this, "No Movies with the name found", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Log.d("ERROR MESSAGE", message);
            Toast.makeText(MainActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(SearchResponse response) {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        adapter=new HomeRecyclerAdapter(this,response.getSearch(),this);
        recycler_view.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id) {
        startActivity(new Intent(MainActivity.this,MovieDetails.class).putExtra("data",id));
    }
}
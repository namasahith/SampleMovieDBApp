package com.example.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.Listeners.OnMovieClickListener;
import com.example.moviedb.Models.SearchObject;
import com.example.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    Context context;
    List<SearchObject> list;
    OnMovieClickListener listener;

    public HomeRecyclerAdapter(Context context, List<SearchObject> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        homeViewHolder.movie_name.setText(list.get(i).getTitle());
        homeViewHolder.movie_name.setSelected(true);
        Picasso.get().load(list.get(i).getPoster()).into(homeViewHolder.poster);
        homeViewHolder.movie_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMovieClicked(list.get(homeViewHolder.getAdapterPosition()).getImdbID());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
}

class HomeViewHolder extends RecyclerView.ViewHolder{
    TextView movie_name;
    CardView movie_container;
    ImageView poster;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        movie_name=itemView.findViewById(R.id.movie_name);
        movie_container=itemView.findViewById(R.id.movie_container);
        poster=itemView.findViewById(R.id.poster);
    }
}

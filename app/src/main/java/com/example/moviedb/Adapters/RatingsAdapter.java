package com.example.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.Models.Rating;
import com.example.moviedb.R;

import java.util.List;

public class RatingsAdapter extends RecyclerView.Adapter<RatingViewHolder> {
    Context context;
    List<Rating> list;

    public RatingsAdapter(Context context, List<Rating> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatingViewHolder(LayoutInflater.from(context).inflate(R.layout.rating_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        holder.movie_detail_source.setText(list.get(position).getSource());
        holder.movie_detail_score.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
}

class RatingViewHolder extends RecyclerView.ViewHolder{
    TextView movie_detail_source,movie_detail_score;
    public RatingViewHolder(@NonNull View itemView) {
        super(itemView);
        movie_detail_score=itemView.findViewById(R.id.movie_detail_score);
        movie_detail_source=itemView.findViewById(R.id.movie_detail_source);
    }
}
package com.encus.mymovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.encus.mymovie.R;
import com.encus.mymovie.viewModels.ResultsItem;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderMovie> {

    private List<ResultsItem> resultsItems;
    private Context context;

    public MovieAdapter(List<ResultsItem> resultsItems, Context context) {
        this.resultsItems = resultsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolderMovie holder, int position) {
        String pathPosterMovie = resultsItems.get(position).getPosterPath();
        System.out.println(pathPosterMovie.toUpperCase());

        // insert image path poster movie to movie_item xml
        String urlImageTheMovieDb = "https://image.tmdb.org/t/p/w500";
        Glide.with(context).asBitmap().load(urlImageTheMovieDb + pathPosterMovie).into(holder.ivPosterPathMovie);
        // insert title movie to textView movie_item xml
        holder.tvTitle.setText(resultsItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public static class ViewHolderMovie extends RecyclerView.ViewHolder {
        ImageView ivPosterPathMovie;
        TextView tvTitle;

        public ViewHolderMovie(@NonNull View itemView) {
            super(itemView);

            ivPosterPathMovie = itemView.findViewById(R.id.poster_path_movie);
            tvTitle = itemView.findViewById(R.id.title_movie);

        }
    }
}

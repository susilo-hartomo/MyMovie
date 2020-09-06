package com.encus.mymovie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.encus.mymovie.adapter.MovieAdapter;
import com.encus.mymovie.networks.ApiClient;
import com.encus.mymovie.viewModels.MovieNowPlayingResponse;
import com.encus.mymovie.viewModels.ResultsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_movie_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String apiKey = "4599c209a1f17f6e495a5b5d91f32a00";
        String language = "en-US";
        int page = 1;

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false );
        dialog.setMessage("Loading ....");
        dialog.show();

        ApiClient.service.moviePlayingResponse(apiKey, language, page).enqueue(new Callback<MovieNowPlayingResponse>() {
           @Override
            public void onResponse(Call<MovieNowPlayingResponse> call, Response<MovieNowPlayingResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    MovieNowPlayingResponse movieNowPlayingResponse = response.body();
                    assert movieNowPlayingResponse != null;
                    List<ResultsItem> resultsItems = movieNowPlayingResponse.getResults();
                    MovieAdapter movieAdapter = new MovieAdapter(resultsItems, MainActivity.this);
                    movieAdapter.notifyDataSetChanged();

                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieNowPlayingResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        
            }
        });
    }
}
package com.encus.mymovie.networks;

import com.encus.mymovie.viewModels.MovieNowPlayingResponse;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("movie/now_playing")
    Call<MovieNowPlayingResponse> moviePlayingResponse (
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

}

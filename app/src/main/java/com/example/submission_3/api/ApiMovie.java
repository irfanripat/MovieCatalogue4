package com.example.submission_3.api;

import com.example.submission_3.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovie {
    @GET("discover/movie")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,@Query("language") String language);

}


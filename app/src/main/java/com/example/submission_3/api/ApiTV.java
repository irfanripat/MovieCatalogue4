package com.example.submission_3.api;

import com.example.submission_3.model.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiTV {
    @GET("discover/tv")
    Call<TVShowResponse> getTopRatedTvShow(@Query("api_key") String apiKey,@Query("language") String language);

}

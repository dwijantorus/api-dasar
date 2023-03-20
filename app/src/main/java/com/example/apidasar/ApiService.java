package com.example.apidasar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ResponsePopularMovie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Movie> getDetailMovie(@Path("movie_id") String movie_id, @Query("api_key") String apiKey);
}
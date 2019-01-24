package com.sarah.android.mymovie.api;

import com.sarah.android.mymovie.MoviesResponse;
import com.sarah.android.mymovie.db.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

   /* @GET("discover/movie?sort_by=popularity.desc")
    Call<MoviesResponse> getPopularMovies( @Query("api_key") String apiKey);
*/
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MoviesResponse> getSearchMovie(@Query("api_key") String apiKey,
                                          @Query("query") String query_movie_title);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") int id, @Query("api_key") String apiKey);
}
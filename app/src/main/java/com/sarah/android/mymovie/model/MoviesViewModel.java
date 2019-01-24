package com.sarah.android.mymovie.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sarah.android.mymovie.MovieUtil;
import com.sarah.android.mymovie.MoviesResponse;
import com.sarah.android.mymovie.api.ApiClient;
import com.sarah.android.mymovie.api.ApiInterface;
import com.sarah.android.mymovie.db.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesViewModel extends ViewModel {
        private static final String TAG = MoviesViewModel.class.getSimpleName();
        private final static String API_KEY = "afe1bf49290012fa8f3230d9993f1e2d";
        private MutableLiveData<List<Movie>> movieList;

        public LiveData<List<Movie>> getMovies(int type) {
            //if the list is null
            if (movieList == null) {
                movieList = new MutableLiveData<List<Movie>>();
                //we will load it asynchronously from server in this method
                loadMovies(type, "");

            }

            //finally we will return the list
            return movieList;
        }

    public LiveData<List<Movie>> getSearchMovies(String name) {
        //if the list is null
        if (movieList == null) {
            movieList = new MutableLiveData<List<Movie>>();
            //we will load it asynchronously from server in this method
            loadMovies(MovieUtil.SEARCH_MOVIE, name);

        }

        //finally we will return the list
        return movieList;
    }

        //This method is using Retrofit to get the JSON data from URL
        private void loadMovies(int type, String name) {
            Call<MoviesResponse> call = null;
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            if(type == MovieUtil.TOP_MOVIE) {
                call = apiService.getTopRatedMovies(API_KEY);
            }
            else if(type == MovieUtil.POPULAR_MOVIE) {
                call = apiService.getPopularMovies(API_KEY);
            }
            else if(type == MovieUtil.SEARCH_MOVIE) {
                call = apiService.getSearchMovie(API_KEY, name);
            }
            else if(type == MovieUtil.COMING_MOVIE) {
                call = apiService.getUpcomingMovies(API_KEY);
            }
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    List<Movie> movies = response.body().getResults();
                    movieList.setValue(movies);
                    Log.d(TAG, "Number of movies received: " + movies.size());
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
            

        }
    }

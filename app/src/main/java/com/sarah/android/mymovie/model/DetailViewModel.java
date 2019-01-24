package com.sarah.android.mymovie.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sarah.android.mymovie.api.ApiClient;
import com.sarah.android.mymovie.api.ApiInterface;
import com.sarah.android.mymovie.db.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<Movie> detailMovie;
    private final static String API_KEY = "afe1bf49290012fa8f3230d9993f1e2d";

    public LiveData<Movie> getMovie(int id) {
        if (detailMovie == null) {
            detailMovie = new MutableLiveData<Movie>();
            //we will load it asynchronously from server in this method
            loadMovie(id);

        }

        return detailMovie;
    }



    private void loadMovie(int id) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getMovieDetail(id, API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie>call, Response<Movie> response) {
                Movie movie = response.body();
                //MoviesResponse res = response.body();
                detailMovie.setValue(movie);

            }

            @Override
            public void onFailure(Call<Movie>call, Throwable t) {
                // Log error here since request failed

            }
        });


    }
}
package com.sarah.android.mymovie.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private MovieRepository mRepository;

    private LiveData<List<FavoriteMovie>> mAllMovies;

    public FavoriteViewModel (Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
    }

    public LiveData<List<FavoriteMovie>> getAllMovies() { return mAllMovies; }

    public void insert(FavoriteMovie movie) {

        mRepository.insert(movie);
    }

    public void deleteAll() {mRepository.deleteAll();}

    public void deleteMovie(FavoriteMovie movie) {mRepository.deleteMovie(movie);}
}
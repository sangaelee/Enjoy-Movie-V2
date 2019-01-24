package com.sarah.android.mymovie.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<FavoriteMovie>> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDb db = MovieRoomDb.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    LiveData<List<FavoriteMovie>> getAllMovies() {
        return mAllMovies;
    }


    public void insert(FavoriteMovie movie) {
        new insertAsyncTask(mMovieDao).execute(movie);
    }

    public void deleteAll() {
        new deleteAllMoviesAsyncTask(mMovieDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteMovie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteMovie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao mAsyncTaskDao;

        deleteAllMoviesAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteMovieAsyncTask extends AsyncTask<FavoriteMovie, Void, Void> {
        private MovieDao mAsyncTaskDao;

        deleteMovieAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteMovie... params) {
            mAsyncTaskDao.deleteMovie(params[0]);
            return null;
        }
    }

    public void deleteMovie(FavoriteMovie word) {
        new deleteMovieAsyncTask(mMovieDao).execute(word);
    }


}
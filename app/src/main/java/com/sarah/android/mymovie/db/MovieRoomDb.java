package com.sarah.android.mymovie.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {FavoriteMovie.class}, version = 1)
public abstract class MovieRoomDb extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile MovieRoomDb INSTANCE;

    static MovieRoomDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDb.class, "movie_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovieDao mDao;

        // Initial data set
        private static String [] movies = {"dolphin", "crocodile", "cobra", "elephant", "goldfish",
                "tiger", "snake"};

        PopulateDbAsync(MovieRoomDb db) {
            mDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            if (mDao.getAnyMovie().length < 1) {
                for (int i = 0; i <= movies.length - 1; i++) {
             //       FavoriteMovie movie = new FavoriteMovie(movies[i]);
             //       mDao.insert(movie);
                }
            }
            return null;
        }
    }

}
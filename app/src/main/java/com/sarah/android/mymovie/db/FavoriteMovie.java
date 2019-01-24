package com.sarah.android.mymovie.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "movie_table")
public class FavoriteMovie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int movieid;

    @NonNull
    @ColumnInfo(name = "movie")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(@NonNull int movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
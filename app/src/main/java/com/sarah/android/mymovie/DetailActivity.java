package com.sarah.android.mymovie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sarah.android.mymovie.db.FavoriteMovie;
import com.sarah.android.mymovie.db.FavoriteViewModel;
import com.sarah.android.mymovie.db.Movie;
import com.sarah.android.mymovie.model.DetailViewModel;

public class DetailActivity extends AppCompatActivity {
    private String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    TextView title, date, popular, overview;
    ImageView backdrop;
    Button detail_done, favorite;
    private String MOVIE_IMAGE_URL="https://image.tmdb.org/t/p/w185";

    String rTitle;
    int rId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.detail_title);
        date = findViewById(R.id.detail_date);
        popular = findViewById(R.id.detail_popular);
        overview = findViewById(R.id.detail_overview);
        overview.setMovementMethod(new ScrollingMovementMethod());
        backdrop = findViewById(R.id.detail_backpath);


        Intent intent = getIntent();
        rId = intent.getIntExtra("movieID", -1);
        rTitle = intent.getStringExtra("movieTitle");

        DetailViewModel model = ViewModelProviders.of(this).get(DetailViewModel.class);

        model.getMovie(rId).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                updateUI(movie);
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteViewModel mFavoriteViewModel = ViewModelProviders.of(DetailActivity.this).get(FavoriteViewModel.class);
                FavoriteMovie fmovie = new FavoriteMovie();
                fmovie.setMovieid(rId);
                fmovie.setTitle(rTitle);
                mFavoriteViewModel.insert(fmovie);

                Intent i = new Intent(DetailActivity.this, FavoriteActivity.class);
                startActivity(i);
            }
        });
        */

    }

    public void updateUI(Movie movie){
        title.setText(movie.getTitle());
        date.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());
        String posterPath =MOVIE_BASE_URL + movie.getPosterPath();
        //Picasso.get().load(posterPath).into(backdrop);
        Glide.with(this)
                .load(MOVIE_IMAGE_URL + movie.getPosterPath())
                .into(backdrop);

        //Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.layout_animation_fall_down);
        backdrop.startAnimation(animation);
        popular.setText(String.valueOf(movie.getPopularity()));
    }
}
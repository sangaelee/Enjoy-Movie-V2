package com.sarah.android.mymovie;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;

import com.sarah.android.mymovie.db.FavoriteMovie;
import com.sarah.android.mymovie.db.FavoriteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*RecyclerView recyclerView;
    MovieAdapter adapter;

    List<Movie> heroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MoviesViewModel model = ViewModelProviders.of(this).get(MoviesViewModel.class);

        model.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                adapter = new MovieAdapter(MainActivity.this, movieList, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });


                FavoriteViewModel mFavoriteViewModel  = ViewModelProviders.of(this).get(FavoriteViewModel.class);
                FavoriteMovie fmovie = new FavoriteMovie();
                fmovie.setMovieid( movie.getId());
                fmovie.setTitle(movie.getTitle());
                mFavoriteViewModel.insert(fmovie);

                Intent i = new Intent(this, FavoriteActivity.class);
                startActivity(i);

    }

    public void onMovieSelected(Movie movie) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("movieID", movie.getId());
        i.putExtra("movieTitle", movie.getTitle());
        startActivity(i);
    }
*/


    private Toolbar mToolbar;
    private TabLayout mTablayout;
    private TabsPagerAdapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.viewpager);
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new PopFragment(), "Popular Movie");
        mAdapter.addFragment(new TopFragment(), "Top Movie");
        mAdapter.addFragment(new ComingFragment(), "UpComing Movie");
        mAdapter.addFragment(new SearchFragment(), "Search Movie");
        mViewPager.setAdapter(mAdapter);

        mTablayout = (TabLayout) findViewById(R.id.tabs);
        mTablayout.setupWithViewPager(mViewPager);
        //       setupTabIcons();


    }
}

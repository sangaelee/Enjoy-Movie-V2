package com.sarah.android.mymovie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarah.android.mymovie.db.Movie;
import com.sarah.android.mymovie.model.MoviesViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComingFragment extends Fragment implements MovieAdapter.AdapterListener{
    RecyclerView recyclerView;
    MovieAdapter adapter;

    public ComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coming, container, false);
        recyclerView = view.findViewById(R.id.recycleComingMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MoviesViewModel model = ViewModelProviders.of(this).get(MoviesViewModel.class);

        model.getMovies(MovieUtil.COMING_MOVIE).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                adapter = new MovieAdapter(getContext(), movieList, ComingFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });

        return view;

    }

    public void onMovieSelected(Movie movie) {
        Intent i = new Intent(getContext(), DetailActivity.class);
        i.putExtra("movieID", movie.getId());
        i.putExtra("movieTitle", movie.getTitle());
        startActivity(i);
    }

}

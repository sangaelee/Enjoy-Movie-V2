package com.sarah.android.mymovie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sarah.android.mymovie.db.Movie;
import com.sarah.android.mymovie.model.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements MovieAdapter.AdapterListener{
    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;
    RecyclerView recyclerView;
    EditText searchQuery;
    AppBarLayout appBarLayout;
    ImageButton clearSearchQuery, imageSearchQuery;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerview_search);
        searchQuery = getView().findViewById(R.id.search_query);
        clearSearchQuery = getView().findViewById(R.id.clear_search_query);
        imageSearchQuery = getView().findViewById(R.id.image_search_query);
        appBarLayout = getView().findViewById(R.id.appBarLayout);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        imageSearchQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchQuery.getText().toString() != "" && !TextUtils.isEmpty(searchQuery.getText().toString())){
                    appBarLayout.setExpanded(false,true);
                    MoviesViewModel model = ViewModelProviders.of(SearchFragment.this).get(MoviesViewModel.class);

                    model.getSearchMovies(searchQuery.getText().toString()).observe(SearchFragment.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(@Nullable List<Movie> movieList) {
                            adapter = new MovieAdapter(getContext(), movieList, SearchFragment.this);
                            recyclerView.setAdapter(adapter);
                        }
                    });


                } else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Empty", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null);
                    View sbView = snackbar.getView();
                    sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                    snackbar.show();
                }
            }
        });
        clearSearchQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery.setText("");
            }
        });
    }



    public void onMovieSelected(Movie movie) {
        Intent i = new Intent(getContext(), DetailActivity.class);
        i.putExtra("movieID", movie.getId());
        i.putExtra("movieTitle", movie.getTitle());
        startActivity(i);
    }
}
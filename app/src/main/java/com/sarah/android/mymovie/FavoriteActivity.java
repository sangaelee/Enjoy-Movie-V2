package com.sarah.android.mymovie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sarah.android.mymovie.db.FavoriteMovie;
import com.sarah.android.mymovie.db.FavoriteViewModel;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private FavoriteViewModel mFavoriteViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.recyclerview_favorite);


        final FavoriteAdapter adapter = new FavoriteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        mFavoriteViewModel.getAllMovies().observe(this, new Observer<List<FavoriteMovie>>() {
            @Override
            public void onChanged(@Nullable final List<FavoriteMovie> movies) {
                adapter.setMovies(movies);
                recyclerView.setAdapter(adapter);

            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        FavoriteMovie myMovie = adapter.getWordAtPosition(position);
                        Toast.makeText(FavoriteActivity.this, "Deleting " +
                                myMovie.getTitle(), Toast.LENGTH_LONG).show();
                        mFavoriteViewModel.deleteMovie(myMovie);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, "Clearing the data...",
                    Toast.LENGTH_SHORT).show();

            // Delete the existing data
            mFavoriteViewModel.deleteAll();
            return true;
        }

        if (id == R.id.exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

/*
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("detailID", -1);
 */
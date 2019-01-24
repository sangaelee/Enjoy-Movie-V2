package com.sarah.android.mymovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarah.android.mymovie.db.FavoriteMovie;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;

        private FavoriteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView_favorite);
        }
    }

    private final LayoutInflater mInflater;
    private List<FavoriteMovie> mMovies; // Cached copy of words

    FavoriteAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_favorite, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        if (mMovies != null) {
            FavoriteMovie current = mMovies.get(position);
            holder.titleTextView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.titleTextView.setText("No Movie");
        }
    }

    void setMovies(List<FavoriteMovie> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mMovies != null)
            return mMovies.size();
        else return 0;
    }

    public FavoriteMovie getWordAtPosition (int position) {
        return mMovies.get(position);
    }


}
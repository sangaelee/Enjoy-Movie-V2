package com.sarah.android.mymovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sarah.android.mymovie.db.Movie;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

        Context mCtx;
        List<Movie> movieList;
        private String MOVIE_IMAGE_URL="https://image.tmdb.org/t/p/w185";
        private AdapterListener listener;

        public MovieAdapter(Context mCtx, List<Movie> movieList,  AdapterListener listener) {
            this.mCtx = mCtx;
            this.movieList = movieList;
            this.listener = listener;
        }

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item_row, parent, false);
            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            Movie movie = movieList.get(position);

            Glide.with(mCtx)
                    .load(MOVIE_IMAGE_URL + movie.getPosterPath())
                    .into(holder.imageView);

            holder.title.setText(movie.getTitle());
            holder.date.setText(movie.getReleaseDate());
            holder.rate.setText(String.valueOf(movie.getVoteAverage()));


            Animation animation = AnimationUtils.loadAnimation(mCtx, android.R.anim.slide_in_left);
            //Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.layout_animation_fall_down);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }

        class MovieViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView title, date, rate ;

            public MovieViewHolder(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                title = itemView.findViewById(R.id.textView);
                date = itemView.findViewById(R.id.textView_date);
                rate = itemView.findViewById(R.id.textView_rate);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // send selected contact in callback
                        listener.onMovieSelected(movieList.get(getAdapterPosition()));

                        //Toast.makeText(context, wList.get(getAdapterPosition()).getCity(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        public interface AdapterListener {
            void onMovieSelected(Movie mMovie);
        }
}
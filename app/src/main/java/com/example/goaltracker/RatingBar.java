package com.example.goaltracker;

import android.widget.ImageView;
import android.util.Log;

public class RatingBar {

    private final ImageView[] stars;

    private int rating = 0;



    public RatingBar(ImageView... stars) {
        this.stars = stars;
    }

    public void setRating(int rating) {

        this.rating = rating;

        Log.d("RATING", "Rating = " + rating);

        for (int i = 0; i < stars.length; i++) {

            if (i < rating) {
                stars[i].setImageResource(R.drawable.selectedstar);
            } else {
                stars[i].setImageResource(R.drawable.group_1);
            }

        }
    }

    public int getRating() {
        return rating;
    }
}
package com.epicodus.feedme.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.feedme.Constants;
import com.epicodus.feedme.R;
import com.epicodus.feedme.models.Foodtruck;
import com.epicodus.feedme.ui.FoodtruckDetailActivity;
import com.epicodus.feedme.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.io.IOException;
import java.util.ArrayList;

import static com.epicodus.feedme.R.id.categoryTextView;
import static com.epicodus.feedme.R.id.ratingTextView;
import static com.epicodus.feedme.R.id.foodtruckImageView;
import static com.epicodus.feedme.ui.FoodtruckDetailFragment.decodeFromFirebaseBase64;



public class FirebaseFoodtruckViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mFoodtruckImageView;

    public FirebaseFoodtruckViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindFoodtruck(Foodtruck foodtruck) {
        mFoodtruckImageView = (ImageView) mView.findViewById(foodtruckImageView);
        TextView mNameTextView = (TextView) mView.findViewById(R.id.foodtruckNameTextView);
        TextView mCategoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView mRatingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        if (!foodtruck.getImageUrl().contains("http")) {
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(foodtruck.getImageUrl());
                mFoodtruckImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        Picasso.with(mContext)
                .load(foodtruck.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mFoodtruckImageView);

        mNameTextView.setText(foodtruck.getName());
        mCategoryTextView.setText(foodtruck.getCategories().get(0));
        mRatingTextView.setText("Rating: " + foodtruck.getRating() + "/5");
    }
        mNameTextView.setText(foodtruck.getName());
        mCategoryTextView.setText(foodtruck.getCategories().get(0));
        mRatingTextView.setText("Rating: " + foodtruck.getRating() + "/5");
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}


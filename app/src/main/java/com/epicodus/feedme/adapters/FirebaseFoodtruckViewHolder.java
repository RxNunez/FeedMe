package com.epicodus.feedme.adapters;

import android.content.Context;
import android.content.Intent;
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
//import com.epicodus.feedme.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;



public class FirebaseFoodtruckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseFoodtruckViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindFoodtruck(Foodtruck foodtruck) {
        ImageView foodtruckImageView = (ImageView) mView.findViewById(R.id.foodtruckImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.foodtruckNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(foodtruck.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(foodtruckImageView);

        nameTextView.setText(foodtruck.getName());
        categoryTextView.setText(foodtruck.getCategories().get(0));
        ratingTextView.setText("Rating: " + foodtruck.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Foodtruck> foodtrucks = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FOODTRUCKS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    foodtrucks.add(snapshot.getValue(Foodtruck.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, FoodtruckDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("foodtrucks", Parcels.wrap(foodtrucks));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

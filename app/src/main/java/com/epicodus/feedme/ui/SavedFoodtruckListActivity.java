package com.epicodus.feedme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.feedme.Constants;
import com.epicodus.feedme.R;
import com.epicodus.feedme.adapters.FirebaseFoodtruckViewHolder;
import com.epicodus.feedme.models.Foodtruck;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFoodtruckListActivity extends AppCompatActivity {
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed_me);
        ButterKnife.bind(this);

        mFoodtruckReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FOODTRUCKS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Foodtruck, FirebaseFoodtruckViewHolder>
                (Foodtruck.class, R.layout.foodtruck_list_item, FirebaseFoodtruckViewHolder.class,
                        mFoodtruckReference) {

            @Override
            protected void populateViewHolder(FirebaseFoodtruckViewHolder viewHolder, Foodtruck model, int position) {
                viewHolder.bindFoodtruck(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
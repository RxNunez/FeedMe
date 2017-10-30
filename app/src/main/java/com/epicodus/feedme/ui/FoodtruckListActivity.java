package com.epicodus.feedme.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.feedme.R;
import com.epicodus.feedme.adapters.FoodtruckListAdapter;
import com.epicodus.feedme.models.Foodtruck;
import com.epicodus.feedme.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FoodtruckListActivity extends AppCompatActivity {
//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private FoodtruckListAdapter mAdapter;
    public ArrayList<Foodtruck> mFoodtrucks = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getFoodtrucks(location);
//            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//
//            if (mRecentAddress != null) {
//                getFoodtrucks(mRecentAddress);
//            }
    }

    private void getFoodtrucks(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findFoodtrucks(location, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                mFoodtrucks = yelpService.processResults(response);

                FoodtruckListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new FoodtruckListAdapter(getApplicationContext(), mFoodtrucks);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(FoodtruckListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }

                });
            }
        });
    }
}


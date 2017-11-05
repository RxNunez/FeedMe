package com.epicodus.feedme.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.feedme.Constants;
import com.epicodus.feedme.R;
import com.epicodus.feedme.adapters.FoodtruckListAdapter;
import com.epicodus.feedme.models.Foodtruck;
import com.epicodus.feedme.services.YelpService;
import com.epicodus.feedme.util.OnFoodtruckSelectedListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FoodtruckListActivity extends AppCompatActivity implements OnFoodtruckSelectedListener {
    private Integer mPosition;
    ArrayList<Foodtruck> mFoodtrucks;
    String mSource;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;
//
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    private FoodtruckListAdapter mAdapter;
//    public ArrayList<Foodtruck> foodtrucks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me);
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//
//        getFoodtrucks(location);
//            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//
//            if (mRecentAddress != null) {
//                getFoodtrucks(mRecentAddress);
//            }
//    }
        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mFoodtrucks = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_FOODTRUCKS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mFoodtrucks != null) {
                    Intent intent = new Intent(this, FoodtruckDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_FOODTRUCKS, Parcels.wrap(mFoodtrucks));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }

            }

        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mFoodtrucks != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_FOODTRUCKS, Parcels.wrap(mFoodtrucks));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }

    @Override
    public void onFoodtruckSelected(Integer position, ArrayList<Foodtruck> foodtrucks, String source) {
        mPosition = position;
        mFoodtrucks = foodtrucks;
        mSource = source;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getFoodtrucks(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//    private void getFoodtrucks(String location) {
//        final YelpService yelpService = new YelpService();
//        yelpService.findFoodtrucks(location, new Callback() {
//
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response){
//                foodtrucks = yelpService.processResults(response);
//
//                FoodtruckListActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        mAdapter = new FoodtruckListAdapter(getApplicationContext(), foodtrucks);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(FoodtruckListActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//
//                });
//            }
//        });
//    }
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

}


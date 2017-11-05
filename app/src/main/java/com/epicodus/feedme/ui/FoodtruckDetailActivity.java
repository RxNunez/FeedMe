package com.epicodus.feedme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.epicodus.feedme.Constants;
import com.epicodus.feedme.R;
import com.epicodus.feedme.adapters.FoodtruckPagerAdapter;
import com.epicodus.feedme.models.Foodtruck;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodtruckDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private FoodtruckPagerAdapter adapterViewPager;
    ArrayList<Foodtruck> mFoodtrucks = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck_detail);
        ButterKnife.bind(this);

        mFoodtrucks = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_FOODTRUCKS));
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new FoodtruckPagerAdapter(getSupportFragmentManager(), mFoodtrucks, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

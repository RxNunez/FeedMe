package com.epicodus.feedme.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.feedme.models.Foodtruck;
import com.epicodus.feedme.ui.FoodtruckDetailFragment;

import java.util.ArrayList;

public class FoodtruckPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Foodtruck> mFoodtrucks;

    public FoodtruckPagerAdapter(FragmentManager fm, ArrayList<Foodtruck> foodtrucks) {
        super(fm);
        mFoodtrucks = foodtrucks;
    }

    @Override
    public Fragment getItem(int position) {
        return FoodtruckDetailFragment.newInstance(mFoodtrucks.get(position));
    }

    @Override
    public int getCount() {
        return mFoodtrucks.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return mFoodtrucks.get(position).getName();
    }
}

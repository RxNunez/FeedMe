package com.epicodus.feedme;

import android.content.Context;
import android.widget.ArrayAdapter;



public class FeedMeArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFoodtruck;
    private String[] mCuisines;

    public FeedMeArrayAdapter(Context mContext, int resource, String[] mFoodtruck, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFoodtruck = mFoodtruck;
        this.mCuisines = mCuisines;
    }
    @Override
    public Object getItem(int position) {
        String foodtruck = mFoodtruck[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", foodtruck, cuisine);
    }

    @Override
    public int getCount() {
        return mFoodtruck.length;
    }
}

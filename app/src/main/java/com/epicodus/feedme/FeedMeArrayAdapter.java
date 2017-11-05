package com.epicodus.feedme;

import android.content.Context;
import android.widget.ArrayAdapter;



public class FeedMeArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFoodtrucks;
    private String[] mCuisines;

    public FeedMeArrayAdapter(Context mContext, int resource, String[] mFoodtrucks, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFoodtrucks = mFoodtrucks;
        this.mCuisines = mCuisines;
    }
    @Override
    public Object getItem(int position) {
        String foodtruck = mFoodtrucks[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", foodtruck, cuisine);
    }

    @Override
    public int getCount() {
        return mFoodtrucks.length;
    }
}

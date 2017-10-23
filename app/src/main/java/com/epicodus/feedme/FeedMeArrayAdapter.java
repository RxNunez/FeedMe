package com.epicodus.feedme;

import android.content.Context;
import android.widget.ArrayAdapter;



public class FeedMeArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFoodtruck;
    private String[] mCuisines;

    public FeedMeArrayAdapter(Context mContext, int resource, String[] mFoodtruck) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFoodtruck = mFoodtruck;

    }
    @Override
    public Object getItem(int position) {
        String Foodtruck = mFoodtruck[position];
        return String.format("%s \nServes great: %s", Foodtruck);
    }

    @Override
    public int getCount() {
        return mFoodtruck.length;
    }
}

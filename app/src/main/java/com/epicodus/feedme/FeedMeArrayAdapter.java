package com.epicodus.feedme;

import android.content.Context;
import android.widget.ArrayAdapter;



public class FeedMeArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mInformation;
    private String[] mCuisines;

    public FeedMeArrayAdapter(Context mContext, int resource, String[] mInformation, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mInformation = mInformation;
        this.mCuisines = mCuisines;
    }
    @Override
    public Object getItem(int position) {
        String information = mInformation[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", information, cuisine);
    }

    @Override
    public int getCount() {
        return mInformation.length;
    }
}

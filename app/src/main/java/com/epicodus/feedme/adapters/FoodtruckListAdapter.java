package com.epicodus.feedme.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.feedme.Constants;
import com.epicodus.feedme.R;
import com.epicodus.feedme.models.Foodtruck;
import com.epicodus.feedme.ui.FoodtruckDetailActivity;
import com.epicodus.feedme.ui.FoodtruckDetailFragment;
import com.epicodus.feedme.util.OnFoodtruckSelectedListener;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FoodtruckListAdapter extends RecyclerView.Adapter<FoodtruckListAdapter.FoodtruckViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Foodtruck> mFoodtrucks = new ArrayList<>();
    private Context mContext;
    private OnFoodtruckSelectedListener mOnFoodtruckSelectedListener;

    public FoodtruckListAdapter(Context context, ArrayList<Foodtruck> foodtrucks, OnFoodtruckSelectedListener foodtruckSelectedListener) {
        mContext = context;
        mFoodtrucks = foodtrucks;
        mOnFoodtruckSelectedListener = foodtruckSelectedListener;
    }

    @Override
    public FoodtruckListAdapter.FoodtruckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodtruck_list_item, parent, false);
        FoodtruckViewHolder viewHolder = new FoodtruckViewHolder(view, mFoodtrucks, mOnFoodtruckSelectedListener);;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodtruckListAdapter.FoodtruckViewHolder holder, int position) {
        holder.bindFoodtruck(mFoodtrucks.get(position));
    }

    @Override
    public int getItemCount() {
        return mFoodtrucks.size();
    }

    public class FoodtruckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.foodtruckImageView) ImageView mFoodtruckImageView;
        @Bind(R.id.foodtruckNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Foodtruck> mFoodtrucks = new ArrayList<>();
        private OnFoodtruckSelectedListener mFoodtruckSelectedListener;

        public FoodtruckViewHolder(View itemView, ArrayList<Foodtruck> foodtrucks, OnFoodtruckSelectedListener foodtruckSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mFoodtrucks = foodtrucks;
            mFoodtruckSelectedListener = foodtruckSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }


        public void bindFoodtruck(Foodtruck foodtruck) {
            Picasso.with(mContext)
                    .load(foodtruck.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mFoodtruckImageView);

            mNameTextView.setText(foodtruck.getName());
            mCategoryTextView.setText(foodtruck.getCategories().get(0));
            mRatingTextView.setText("Rating: " + foodtruck.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mFoodtruckSelectedListener.onFoodtruckSelected(itemPosition, mFoodtrucks, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
            Intent intent = new Intent(mContext, FoodtruckDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_FOODTRUCKS, Parcels.wrap(mFoodtrucks));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }

        private void createDetailFragment(int position) {
            FoodtruckDetailFragment detailFragment = FoodtruckDetailFragment.newInstance(mFoodtrucks, position, Constants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.foodtruckDetailContainer, detailFragment);
            ft.commit();
        }

    }
}

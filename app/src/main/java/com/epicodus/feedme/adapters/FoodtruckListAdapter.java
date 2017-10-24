package com.epicodus.feedme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.feedme.R;
import com.epicodus.feedme.models.Foodtruck;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FoodtruckListAdapter extends RecyclerView.Adapter<FoodtruckListAdapter.FoodtruckViewHolder> {
    private ArrayList<Foodtruck> mFoodtrucks = new ArrayList<>();
    private Context mContext;

    public FoodtruckListAdapter(Context context, ArrayList<Foodtruck> foodtrucks) {
        mContext = context;
        mFoodtrucks = foodtrucks;
    }

    @Override
    public FoodtruckListAdapter.FoodtruckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodtruck_list_item, parent, false);
        FoodtruckViewHolder viewHolder = new FoodtruckViewHolder(view);
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

    public class FoodtruckViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.foodtruckImageView) ImageView mFoodtruckImageView;
        @Bind(R.id.foodtruckNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public FoodtruckViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindFoodtruck(Foodtruck foodtruck) {
            Picasso.with(mContext).load(foodtruck.getImageUrl()).into(mFoodtruckImageView);
            mNameTextView.setText(foodtruck.getName());
            mCategoryTextView.setText(foodtruck.getCategories().get(0));
            mRatingTextView.setText("Rating: " + foodtruck.getRating() + "/5");
        }
    }
}

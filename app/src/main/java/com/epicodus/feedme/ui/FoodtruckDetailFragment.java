package com.epicodus.feedme.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.feedme.R;
import com.epicodus.feedme.models.Foodtruck;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodtruckDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.foodtruckImageView) ImageView mImageLabel;
    @Bind(R.id.foodtruckNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveFoodtruckButton) TextView mSaveFoodtruckButton;

    private Foodtruck mFoodtruck;

    public static FoodtruckDetailFragment newInstance(Foodtruck foodtruck) {
        FoodtruckDetailFragment foodtruckDetailFragment = new FoodtruckDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("foodtruck", Parcels.wrap(foodtruck));
        foodtruckDetailFragment.setArguments(args);
        return foodtruckDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoodtruck = Parcels.unwrap(getArguments().getParcelable("foodtruck"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foodtruck_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mFoodtruck.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mImageLabel);

        mNameLabel.setText(mFoodtruck.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mFoodtruck.getCategories()));
        mRatingLabel.setText(Double.toString(mFoodtruck.getRating()) + "/5");
        mPhoneLabel.setText(mFoodtruck.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mFoodtruck.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveFoodtruckButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mFoodtruck.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mFoodtruck.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mFoodtruck.getLatitude()
                            + "," + mFoodtruck.getLongitude()
                            + "?q=(" + mFoodtruck.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}
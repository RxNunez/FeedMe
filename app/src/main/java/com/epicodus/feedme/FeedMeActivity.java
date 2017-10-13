package com.epicodus.feedme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FeedMeActivity extends AppCompatActivity {
    public static final String TAG = FeedMeActivity.class.getSimpleName();
    private TextView mInterestTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me);
        mInterestTextView = (TextView) findViewById(R.id.interestTextView);
        Intent intent = getIntent();
        String interest = intent.getStringExtra("interest");
        mInterestTextView.setText("Here is the information you requested: " + interest);
    }
}

package com.epicodus.feedme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findInformationButton) Button mFindInformationButton;
    @Bind(R.id.interestEditText) EditText mInterestEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface PacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(PacificoFont);

        mFindInformationButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
            String interest = mInterestEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, FeedMeActivity.class);
            intent.putExtra("interest", interest);
            startActivity(intent);
            }

    }


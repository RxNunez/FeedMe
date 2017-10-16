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

public class MainActivity extends AppCompatActivity {
    private Button mFindInformationButton;
    private EditText mInterestEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface PacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(PacificoFont);
        mInterestEditText = (EditText) findViewById(R.id.interestEditText);
        mFindInformationButton = (Button) findViewById(R.id.findInformationButton);
        mFindInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String interest = mInterestEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, FeedMeActivity.class);
                intent.putExtra("interest", interest);
                startActivity(intent);
            }
        });
    }
}

package com.epicodus.feedme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindInformationButton;
    private EditText mInterestEditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterestEditText = (EditText) findViewById(R.id.InterestEditText);
        mFindInformationButton = (Button) findViewById(R.id.findInformationButton);
        mFindInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String interest = mInterestEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, FeedMeActivity.class);
                intent.putExtra("Interest", Interest);
                startActivity(intent);
            }
        });
    }
}

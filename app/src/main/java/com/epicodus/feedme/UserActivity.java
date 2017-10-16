package com.epicodus.feedme;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserActivity extends AppCompatActivity {
    @Bind(R.id.userButton)
    Button userButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                UserDialogFragment userDialogFragment = new UserDialogFragment();
                userDialogFragment.show(fm, "Sample Fragment");
            }
        });
    }
    }


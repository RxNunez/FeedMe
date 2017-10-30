package com.epicodus.feedme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.epicodus.feedme.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.findFoodtrucksButton)
    Button mFindFoodtrucksButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface PacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(PacificoFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();


        mFindFoodtrucksButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindFoodtrucksButton) {
            String location = mLocationEditText.getText().toString();

            saveLocationToFirebase(location);

//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
            Intent intent = new Intent(MainActivity.this, FoodtruckListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
}



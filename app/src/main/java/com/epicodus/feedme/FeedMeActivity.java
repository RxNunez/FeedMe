package com.epicodus.feedme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.widget.AdapterView.*;

public class FeedMeActivity extends AppCompatActivity {
    private TextView mInterestTextView;
    private ListView mListView;
    private String[] information = new String[] {"Italy", "Mexico",
            "Thailand", "Japan", "America", "Russia",
            "Columbia", "Spain", "Australia", "Brazil",
            "Korea", "Philipines", "Venezula",
            "France", "Middle East"};
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        mInterestTextView = (TextView) findViewById(R.id.interestTextView);

        FeedMeArrayAdapter adapter = new FeedMeArrayAdapter(this, android.R.layout.simple_list_item_1, information, cuisines);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String information = ((TextView)view).getText().toString();
                Toast.makeText(FeedMeActivity.this, information, Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = getIntent();
        String interest = intent.getStringExtra("interest");
        mInterestTextView.setText("Here is the information you requested: " + interest);
    }
}

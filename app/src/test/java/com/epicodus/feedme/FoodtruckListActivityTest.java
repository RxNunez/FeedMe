package com.epicodus.feedme;

import android.os.Build;
import android.widget.ListView;

import com.epicodus.feedme.ui.FoodtruckListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class FoodtruckListActivityTest {
        private FoodtruckListActivity activity;
        private ListView mFeedMeListView;

        @Before
        public void setup() {
        activity = Robolectric.setupActivity(FoodtruckListActivity.class);
        mFeedMeListView = (ListView) activity.findViewById(R.id.listView);
        }
    @Test
    public void feedMeListViewPopulates() {
        assertNotNull(mFeedMeListView.getAdapter());
        assertEquals(mFeedMeListView.getAdapter().getCount(), 15);
    }

}

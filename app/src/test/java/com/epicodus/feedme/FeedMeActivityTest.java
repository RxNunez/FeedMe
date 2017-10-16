package com.epicodus.feedme;

import android.os.Build;
import android.widget.ListView;

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
public class FeedMeActivityTest {
        private FeedMeActivity activity;
        private ListView mFeedMeListView;

        @Before
        public void setup() {
        activity = Robolectric.setupActivity(FeedMeActivity.class);
        mFeedMeListView = (ListView) activity.findViewById(R.id.listView);
        }
    @Test
    public void feedMeListViewPopulates() {
        assertNotNull(mFeedMeListView.getAdapter());
        assertEquals(mFeedMeListView.getAdapter().getCount(), 15);
    }

}

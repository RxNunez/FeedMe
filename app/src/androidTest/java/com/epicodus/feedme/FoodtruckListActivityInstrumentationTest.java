package com.epicodus.feedme;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.epicodus.feedme.ui.FoodtruckListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;


public class FoodtruckListActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<FoodtruckListActivity> activityTestRule =
            new ActivityTestRule<>(FoodtruckListActivity.class);
    @Test
    public void listItemClickDisplaysToastWithCorrectRestaurant() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String informationName = "Italy";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(informationName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(informationName)));
    }

}

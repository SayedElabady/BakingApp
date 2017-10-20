package sayed.com.bakeryapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sayed.com.bakeryapp.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Sayed on 10/19/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailsActivityTest {

        @Rule
        public ActivityTestRule<MainActivity> activityRule =
                new ActivityTestRule(MainActivity.class);
        private IdlingResource idlingResource;

        @Before
        public void registerIdlingResource() {
                idlingResource = activityRule.getActivity().getIdlingResouce();
                // To prove that the test fails, omit this call:
                Espresso.registerIdlingResources(idlingResource);
        }
        @Test
        public void checkText_MainActivity() {
                onView(ViewMatchers.withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
               onView(withText("Brownies")).check(matches(isDisplayed()));

        }
        @Test
        public void checkREcyclerViews_DetailsActivity() {
                onView(ViewMatchers.withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
                onView(ViewMatchers.withId(R.id.steps_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
             }
        @After
        public void unregisterIdlingResource() {
                if (idlingResource != null) {
                        Espresso.unregisterIdlingResources(idlingResource);
                }
        }

}

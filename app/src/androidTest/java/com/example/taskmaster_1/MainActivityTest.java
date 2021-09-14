package com.example.taskmaster_1;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    public static final String username = "abdallah";
    public static final String titleText = "Name";

    @Test
    public void testSettingButton() {
        onView(withId(R.id.setting)).perform(click());
        onView(withId(R.id.userName1)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.save)).perform(click());
        onView(withId(R.id.userName)).check(matches(withText(titleText+username+ " tasks")));
    }
    
    @Test
    public void testGoToDetails() {
        onView(withId(R.id.taskRecylerView)).perform(actionOnItemAtPosition(0 ,click()));
        onView(withId(R.id.titleTextView)).check(matches(withText(titleText)));

    }
}













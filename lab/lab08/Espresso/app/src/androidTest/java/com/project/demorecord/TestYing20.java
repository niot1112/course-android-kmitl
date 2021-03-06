package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestYing20 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testYing20() {

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTExtName),isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(2000);

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        SystemClock.sleep(5000);

    }
}

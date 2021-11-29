package com.example.foodreserve;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.DatePicker;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.foodreserve.view.MainActivity;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import foodreserve.R;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


/*
 * As object detection is run immediately on UIThread we had to introduce Thread.sleep in order to run these tests
 * Otherwise the espresso clicking tests go very quickly
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test11_useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.foodreserve", appContext.getPackageName());
        Thread.sleep(2000);
    }

    // Move to next picture (move right) and go back to previous picture (left)
    @Test
    public void test21_galleryNavigation() throws InterruptedException {
        onView(withId(R.id.buttonRight)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.buttonLeft)).perform(click());
        Thread.sleep(5000);
    }

    // Move to next picture (move right) and go back to previous picture (left)
    @Test
    public void test22_galleryUpdateCaptionNavigation() throws InterruptedException {
        onView(withId(R.id.buttonRight)).perform(click());
        Thread.sleep(5000);
        // enter text string
        onView(withId(R.id.editTextCaption)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextCaption)).perform(typeText("test caption from espresso"), closeSoftKeyboard());
        // press update and check match
        onView(withId(R.id.btnUpdateCaption)).perform(click());
        onView(withId(R.id.editTextCaption)).check(matches(withText("test caption from espresso")));
    }

    // Move to next picture (move right) and go back to previous picture (left)
    @Test
    public void test23_galleryUpdateCaptionNavigation() throws InterruptedException {
        onView(withId(R.id.buttonRight)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.editTextCaption)).check(matches(withText("test caption from espresso")));

        // enter new text string
        onView(withId(R.id.editTextCaption)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextCaption)).perform(typeText("another caption from espresso"), closeSoftKeyboard());
        // press update and check match
        onView(withId(R.id.btnUpdateCaption)).perform(click());
        onView(withId(R.id.editTextCaption)).check(matches(withText("another caption from espresso")));
    }

   @Test
    public void test35_detect() throws InterruptedException {
        onView(withId(R.id.buttonDetect)).perform(click());
       Thread.sleep(5000);
       onView(withId(R.id.editTextDetectedObject)).check(matches(isDisplayed()));
    }

    @Test
    public void test38_viewRecipes() throws InterruptedException {
        onView(withId(R.id.buttonDetect)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.buttonRecipes)).perform(click());
        Thread.sleep(5000);
        pressBack();
    }

    public void restartActivity() {
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.RESUMED);
        scenario.recreate();
    }
}

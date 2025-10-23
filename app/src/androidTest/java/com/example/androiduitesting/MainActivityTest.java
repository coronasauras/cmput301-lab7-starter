package com.example.androiduitesting;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    /**
     * Test Case 1: Check whether the activity correctly switched
     */
    @Test
    public void testActivitySwitch() {
        // Add city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Check if ShowActivity is displayed by verifying city name text is shown
        onView(withId(R.id.city_name_text)).check(matches(isDisplayed()));
    }

    /**
     * Test Case 2: Check whether city name is consistent
     */
    @Test
    public void testCityNameConsistency() {
        // Add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());
        // Click on city
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());
        // Check correct city name is displayed
        onView(withId(R.id.city_name_text)).check(matches(withText("Vancouver")));
    }

    /**
     * Test Case 3: Check "back" button functionality
     */
    @Test
    public void testBackButton() {
        // Add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());
        // Click on city
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());
        // Click back button
        onView(withId(R.id.back_button)).perform(click());
        // Check that we go back to the home screen by looking for city list
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }

    /**
     * Demo Test
     */
    @Test
    public void testAddCity(){
    // Click on Add City button
        onView(withId(R.id.button_add)).perform(click());
    // Type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
    // Click on Confirm
        onView(withId(R.id.button_confirm)).perform(click());
    // Check if text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    /**
     * Demo Test
     */
    @Test
    public void testClearCity(){
    // Add first city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());
    //Add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Vancouver"));
                onView(withId(R.id.button_confirm)).perform(click());
    //Clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    /**
     * Demo Test
     */
    @Test
    public void testListView(){
    // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());
    // Check if in the Adapter view (given id of that adapter view), there is a data
    // (which is an instance of String) located at position zero.
    // If this data matches the text we provided then Voila! Our test should pass
    // You can also use anything() in place of is(instanceOf(String.class))
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches((withText("Edmonton"))));
    }

}

package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.network.JokeAsyncTask;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.amrelmasry.joke_display.JokeDisplayActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Amr on 21/04/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<>(MainActivity.class, true, true);

    @Test
    public void pressTellMeJoke_shouldDisplayJoke() throws Exception {

        // click on tell joke button
        onView(withId(R.id.tell_joke_btn)).perform(click());

        // assert that asyncTask has generated a valid joke,
        // and sent to the next activity
        intended(
                hasExtras(
                        hasEntry(JokeDisplayActivity.JOKE_EXTRA, hasValidJoke())
                ));
    }

    private Matcher<String> hasValidJoke() {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                String joke = (String) item;
                return !TextUtils.isEmpty(joke);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    @Test
    public void asyncTask_shouldReturnValidJoke() throws Exception {

        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(activityTestRule.getActivity());
        String joke = jokeAsyncTask.execute().get();

        assertNotNull(joke);
        assertTrue(!TextUtils.isEmpty(joke));
    }
}
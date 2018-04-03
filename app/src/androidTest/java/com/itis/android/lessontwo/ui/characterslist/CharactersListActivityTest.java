package com.itis.android.lessontwo.ui.characterslist;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;

import io.realm.Realm;

import org.junit.*;
import org.junit.runner.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.IsNot.not;

/**
 * Created by Home on 03.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class CharactersListActivityTest {

    @Rule
    public final ActivityTestRule<CharactersListActivity> rule =
            new ActivityTestRule<>(CharactersListActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @Test
    public void testRecyclerViewDisplayed() throws Exception{
        onView(withId(R.id.tv_empty)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rv_comics_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollRecyclerView() throws Exception{
        onView(withId(R.id.rv_comics_list))
                .perform(scrollToPosition(4))
                .perform(scrollToPosition(3))
                .perform(scrollToPosition(1))
                .perform(scrollToPosition(2));
    }

    @Test
    public void testClickOnItem() throws Exception{
        onView(withId(R.id.rv_comics_list))
                .perform(actionOnItemAtPosition(3,click()));
        Intents.intended(hasComponent(CharactersListActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception{
        Intents.release();
        Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());
    }
}

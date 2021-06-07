package com.devsoldatenkov.cryptoapp.view.fragments

import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devsoldatenkov.cryptoapp.R
import com.devsoldatenkov.cryptoapp.view.MainActivity
import com.devsoldatenkov.cryptoapp.view.viewholder.MainListItemViewHolder
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recyclerViewShouldBeAttached() {
        Espresso.onView(withId(R.id.main_recycler))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.main_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainListItemViewHolder>(0,
            ViewActions.click()
        ))
    }


    @Test
    fun allMenuDestinationsShouldWork() {
        Espresso.onView(withId(R.id.homeFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.home_root))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.favoritesFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.favorites_root))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldOpenDetailsFragment() {
        Espresso.onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MainListItemViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.details_root))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun addToFavoritesButtonTest() {
        Espresso.onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MainListItemViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.fab_favorites)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.favoritesFragment)).perform(ViewActions.click())
        activityScenarioRule.scenario.onActivity {
            val rv = it.findViewById<RecyclerView>(R.id.favorites_recycler)
            Assert.assertTrue(rv.size != 0)
        }
    }
}

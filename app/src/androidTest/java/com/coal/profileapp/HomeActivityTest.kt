package com.coal.profileapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.coal.profileapp.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun  testSwipeRight() {
        onView(withId(R.id.newCardView))
            .perform(ViewActions.swipeRight())
    }

    @Test
    @Throws(Exception::class)
    fun  testSwipeLeft() {
        onView(withId(R.id.newCardView))
            .perform(ViewActions.swipeLeft())
    }
}
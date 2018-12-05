package com.example.lenovo.footballapps

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.DrawerMatchers.isOpen
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import com.example.lenovo.footballapps.R.id.*
import com.example.lenovo.footballapps.R.menu.activity_main_drawer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun navDraw() {
        //Clik On Fragment Teams
        Thread.sleep(3000)
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_teams))

        //Click On Fragment match Schedule
        Thread.sleep(3000)
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_match_schedule))
        //Click On Fragment match Result
        Thread.sleep(3000)
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_match_result))

        //Click On Fragment search Match
        Thread.sleep(3000)
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_search_matchs))
        Thread.sleep(1000)
        onView(withId(action_search)).perform(click())
        Thread.sleep(2000)
        onView(withId(search_match)).perform(typeText("Arsenal"))
        //Click On Fragment search Team
        Thread.sleep(3000)
        pressBack()
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_search_teams))
        Thread.sleep(1000)
        onView(withId(action_search)).perform(click())
       // onView(withId(search_team)).perform(typeText("Arsenal"))

        //Click On Fragment favorite Teams
        Thread.sleep(3000)
        pressBack()
        pressBack()
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_favorite_teams))


        //Click On Fragment favorite Matchs
        Thread.sleep(3000)
        onView(withId(drawer_layout)).perform(DrawerActions.open())
        Thread.sleep(3000)
        onView(withId(drawer_layout)).check(matches(isOpen()))
        Thread.sleep(3000)
        onView(withId(nav_view)).perform(NavigationViewActions.navigateTo(nav_favorite_matchs))

        Thread.sleep(4000)

    }

    @Test
    fun selectMatch() {
        onView(withId(drawer_layout)).perform(swipeRight())
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()
    }
}
package com.primakpavel.roomsample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    private lateinit var mStringUserName: String

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initUserName() {
        mStringUserName = "_Petro_"
    }

    @Test
    fun useAppContext() {
        // Type text and then press the button.
        onView(withId(R.id.name_input))
                .perform(typeText(mStringUserName), closeSoftKeyboard())
        onView(withId(R.id.add_btn)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.all_users_stuff))
                .check(matches(withText(containsString(mStringUserName))))
    }
}

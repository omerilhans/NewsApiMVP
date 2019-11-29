package com.omerilhanli.newsapimvp

import android.view.View
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.omerilhanli.newsapimvp.mvvm.view.activity.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.omerilhanli.newsapimvp.mvvm.view.adapter.ArticleAdapter
import org.hamcrest.Matcher

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArticlesItemClickTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickItem() {
        onView(withId(R.id.searchView)).perform(ViewActions.typeText(" player"))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.cvItem)
            )
        )
    }

    object MyViewAction {

        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<CardView>(id)
                    v.performClick()
                }
            }
        }

    }
}

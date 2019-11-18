package br.com.renannunessil.popmovies.movieslist


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.movies.activity.MoviesActivity
import br.com.renannunessil.popmovies.movies.movieslist.ui.MoviesListAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesListFragmentTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule<MoviesActivity>(MoviesActivity::class.java)

    @Before
    fun setUp() {
        val intent = rule.activity.intent
        intent.putExtra(Constants.TEST_PARAMETHER, true)
        rule.finishActivity()
        rule.launchActivity(intent)
    }

    @Test
    fun moviesListTest() {
        onView(withId(R.id.rv_movies_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesListAdapter.MoviesListViewHolder>(0, click()))
    }
}
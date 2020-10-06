package br.com.ricardo.reposgithub

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.ricardo.reposgithub.presentation.repolist.ui.RepoListActivity
import br.com.ricardo.reposgithub.presentation.repolist.ui.adapter.RepoListAdapter
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RepoListActivityTest {

    @get:Rule
    val rule = ActivityTestRule<RepoListActivity>(RepoListActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeRepoList.repos[LIST_ITEM_IN_TEST]

    @Test
    fun git_view_flipper_visible_with_progressbar_on_app_launch() {
        onView(withId(R.id.git_view_flipper)).check(matches(isDisplayed()))
    }

    @Test
    fun item_git_swipup() {
        onView(withId(R.id.git_view_flipper)).check(matches(isDisplayed()))

        val maxAttempts = 10
        onView(withId(R.id.git_view_flipper))
            .perform(
                repeatedlyUntil(swipeUp(), hasDescendant(withText(MOVIE_IN_TEST.name)), maxAttempts),
                click()
            )
    }

    @Test
    fun item_git_swipeup_and_back() {
        onView(withId(R.id.git_view_flipper)).check(matches(isDisplayed()))

        val maxAttempts = 10
        onView(withId(R.id.git_view_flipper))
            .perform(
                repeatedlyUntil(swipeUp(), hasDescendant(withText(MOVIE_IN_TEST.name)), maxAttempts),
                click()
            )

        pressBack()
    }

}
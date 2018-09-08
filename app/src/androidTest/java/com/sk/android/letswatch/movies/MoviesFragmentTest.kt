package com.sk.android.letswatch.movies

import android.arch.lifecycle.MutableLiveData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.sk.android.letswatch.MainActivity
import com.sk.android.letswatch.R
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.util.TestUtil
import com.sk.android.letswatch.util.ViewModelUtil
import com.sk.android.letswatch.vo.Resource
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    private val fragment = MoviesFragment()
    private val viewModel = mock(MoviesViewModel::class.java)
    private val liveData = MutableLiveData<Resource<MovieWebServiceResponse>>()

    @Before
    fun init() {
        `when`(viewModel.movies).thenReturn(liveData)
        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        rule.activity.replaceFragment(fragment)
        IdlingRegistry.getInstance().register(CountingIdlingResource("test"))
    }

    @Test
    fun testEmptyRecyclerView() {
        onView(withId(R.id.moviesRecyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testRecyclerViewContainsMovies() {
        val response = TestUtil.testMovieResponse()
        liveData.postValue(Resource.success(response))
        sleep(1000)
        onView(withId(R.id.moviesRecyclerView)).check(matches(isDisplayed()))
    }

}
package com.sk.android.letswatch.movies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.sk.android.letswatch.data.source.MovieRepository
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MoviesViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val request = mock<MovieWebServiceRequest> {
        on { page } doReturn 1
    }

    private val repository = mock<MovieRepository>()
    private val moviesViewModel = MoviesViewModel(TopRatedMoviesUseCase(repository))

    @Test
    fun testNull() {
        assertThat(moviesViewModel.movies, notNullValue())
        assertThat(moviesViewModel.movies.value, nullValue())
        verify(repository, never()).getTopRatedMovies(any())
    }

    @Test
    fun dontFetchWithoutObservers() {
        assertThat(moviesViewModel.movies.hasObservers(), CoreMatchers.`is`(false))
        moviesViewModel.loadTopRatedMovies(request)
        verify(repository, never()).getTopRatedMovies(1)
    }

    @Test
    fun fetchWhenObserved() {
        moviesViewModel.movies.observeForever(mock())
        assertThat(moviesViewModel.movies.hasObservers(), CoreMatchers.`is`(true))
        moviesViewModel.loadTopRatedMovies(request)
        verify(repository).getTopRatedMovies(1)
    }

}
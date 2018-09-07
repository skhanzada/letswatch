package com.sk.android.letswatch.movies

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.sk.android.letswatch.data.source.MovieRepository
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TopRatedMoviesUseCaseTest {

    @Test
    fun testTopRatedMoviesUseCase() {
        val resource = mock<Resource<MovieWebServiceResponse>>()
        val repository = mock<MovieRepository> {
            on { getTopRatedMovies(1) } doReturn resource
        }

        val request = mock<MovieWebServiceRequest> {
            on { page } doReturn 1
        }

        val topRatedMoviesUseCase = TopRatedMoviesUseCase(repository)
        runBlocking {
            topRatedMoviesUseCase(request) {
                assertNotNull(it)
                assertEquals(it, resource)
            }
        }
        verify(repository).getTopRatedMovies(1)
    }

}
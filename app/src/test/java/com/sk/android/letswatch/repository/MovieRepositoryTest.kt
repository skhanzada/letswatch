package com.sk.android.letswatch.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sk.android.letswatch.data.source.MovieRepository
import com.sk.android.letswatch.data.source.remote.MovieRemoteDataSource
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieRepositoryTest {

    @Test
    fun testTopRatedMovies() {
        val resource = mock<Resource<MovieWebServiceResponse>>()
        val datasource = mock<MovieRemoteDataSource> {
            on { getTopRatedMovies(1) } doReturn resource
        }

        val repository = MovieRepository(mock(), datasource)
        runBlocking {
            val response = repository.getTopRatedMovies(1)
            assertEquals(response, resource)
        }

        verify(datasource).getTopRatedMovies(1)
    }

}
package com.sk.android.letswatch.data.source

import com.sk.android.letswatch.data.source.local.MovieLocalDataSource
import com.sk.android.letswatch.data.source.remote.MovieRemoteDataSource
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun getTopRatedMovies(page: Int): Resource<MovieWebServiceResponse> {
        // TODO: need to use local datasource
        return movieRemoteDataSource.getTopRatedMovies(page)
    }

}
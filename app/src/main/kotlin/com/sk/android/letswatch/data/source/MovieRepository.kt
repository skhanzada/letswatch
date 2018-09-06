package com.sk.android.letswatch.data.source

import com.sk.android.letswatch.data.Movie
import com.sk.android.letswatch.data.source.local.MovieLocalDataSource
import com.sk.android.letswatch.data.source.remote.MovieRemoteDataSource
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun getTopRatedMovies(): Resource<List<Movie>> {
        // TODO: need to use local datasource
        return movieRemoteDataSource.getTopRatedMovies()
    }

}
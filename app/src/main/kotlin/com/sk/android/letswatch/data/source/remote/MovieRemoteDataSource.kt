package com.sk.android.letswatch.data.source.remote

import com.google.gson.Gson
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(gson: Gson, val webService: MovieWebService) :
    AbstractRemoteDataSource(gson) {

    fun getTopRatedMovies(nextPage: Int): Resource<MovieWebServiceResponse> {
        return execute(webService.getTopRatedMovies(page = nextPage))
    }

    fun getPopularMovies(): Resource<MovieWebServiceResponse> {
        TODO()
    }

    fun getLatestMovies(): Resource<MovieWebServiceResponse> {
        TODO()
    }

    fun getUpcomingMovies(): Resource<MovieWebServiceResponse> {
        TODO()
    }

    fun getNowPlayingMovies(): Resource<MovieWebServiceResponse> {
        TODO()
    }

}
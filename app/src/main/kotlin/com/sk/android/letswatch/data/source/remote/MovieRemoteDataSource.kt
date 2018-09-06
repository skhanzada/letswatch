package com.sk.android.letswatch.data.source.remote

import com.sk.android.letswatch.data.Movie
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(val webService: MovieWebService) {

    fun getTopRatedMovies(): Resource<List<Movie>> {
        // execute call and return response
        try {
            val response = webService.getTopRatedMovies().execute()
            return if (response.isSuccessful)
                Resource.success(response.body()?.results)
            else
            //TODO need to handle failure later
                Resource.error("Failed to load top rated movies", null)
        } catch (e: Exception) {
            return Resource.error(e.message!!, null)
        }
    }

}
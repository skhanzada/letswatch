package com.sk.android.letswatch.movies

import android.arch.lifecycle.MutableLiveData
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.testing.OpenForTesting
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

@OpenForTesting
class MoviesViewModel @Inject constructor(private val topRatedMoviesUseCase: TopRatedMoviesUseCase) :
    BaseViewModel() {

    val movies = MutableLiveData<Resource<MovieWebServiceResponse>>()

    fun loadTopRatedMovies(request: MovieWebServiceRequest) {
        if (!super.load(movies)) return
        topRatedMoviesUseCase(request) {
            movies.value = it
        }
    }

}
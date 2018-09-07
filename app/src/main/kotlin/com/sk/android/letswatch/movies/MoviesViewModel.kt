package com.sk.android.letswatch.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val topRatedMoviesUseCase: TopRatedMoviesUseCase) :
    ViewModel() {

    val movies = MutableLiveData<Resource<MovieWebServiceResponse>>()

    fun loadTopRatedMovies(request: MovieWebServiceRequest) {
        movies.value = Resource.loading(null)
        topRatedMoviesUseCase(request) {
            movies.value = it
        }
    }

}
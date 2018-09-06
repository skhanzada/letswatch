package com.sk.android.letswatch.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sk.android.letswatch.data.Movie
import com.sk.android.letswatch.data.source.MovieRepository
import com.sk.android.letswatch.vo.Resource
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    val topRatedMovies = MutableLiveData<Resource<List<Movie>>>()

    suspend fun run(): Resource<List<Movie>> {
        return movieRepository.getTopRatedMovies()
    }

    fun loadTopRatedMovies() {
        topRatedMovies.value = Resource.loading(null)
        val job = async(CommonPool) {
            run()
        }
        launch(UI) {
            onResult(job.await())
        }
    }

    private fun onResult(data: Resource<List<Movie>>) {
        topRatedMovies.value = data
    }
}
package com.sk.android.letswatch.data.source.local

import android.arch.lifecycle.LiveData
import com.sk.android.letswatch.data.Movie
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getTopRatedMovies(): LiveData<List<Movie>> {
        return movieDao.getTopRatedMovies()
    }

    fun save(data: List<Movie>) {
        movieDao.save(data)
    }
}
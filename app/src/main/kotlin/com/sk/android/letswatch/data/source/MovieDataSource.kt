package com.sk.android.letswatch.data.source

import com.sk.android.letswatch.data.Movie
import com.sk.android.letswatch.vo.Resource

interface MovieDataSource {
    fun getTopRatedMovies(): Resource<List<Movie>>
}
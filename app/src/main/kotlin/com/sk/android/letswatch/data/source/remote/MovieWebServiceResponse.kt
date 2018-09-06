package com.sk.android.letswatch.data.source.remote

import com.sk.android.letswatch.data.Movie

data class MovieWebServiceResponse(
    val page: Int,
    val results: List<Movie>,
    val totalResults: Int,
    val totalPages: Int
)
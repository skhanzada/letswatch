package com.sk.android.letswatch.util

import com.google.gson.Gson
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import java.io.InputStreamReader

object TestUtil {

    fun testMovieResponse(): MovieWebServiceResponse {
        val reader = InputStreamReader(
            javaClass.classLoader.getResourceAsStream("json/movies.json")
        )
        val response =
            Gson().fromJson<MovieWebServiceResponse>(
                reader, MovieWebServiceResponse::class.java
            )
        reader.close()
        return response
    }

}
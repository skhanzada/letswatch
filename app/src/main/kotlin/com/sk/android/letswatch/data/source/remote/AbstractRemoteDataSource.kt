package com.sk.android.letswatch.data.source.remote

import com.google.gson.Gson
import com.sk.android.letswatch.vo.Resource
import okhttp3.ResponseBody
import retrofit2.Call

abstract class AbstractRemoteDataSource(private val gson: Gson) {

    fun <T, R> execute(webServiceCall: Call<T>): Resource<R> {
        return try {
            val response = webServiceCall.execute()
            if (response.isSuccessful)
                Resource.success(response.body() as R)
            else
                processError(response.errorBody())
        } catch (e: Exception) {
            Resource.error(e.message!!, null)
        }
    }

    private fun processError(response: ResponseBody?): Resource<Nothing> {
        val error = gson.fromJson(response?.string(), ApiError::class.java)
        return Resource.error(error.statusMessage, null)
    }
}
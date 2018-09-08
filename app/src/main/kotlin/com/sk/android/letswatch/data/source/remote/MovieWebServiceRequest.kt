package com.sk.android.letswatch.data.source.remote

import com.sk.android.letswatch.testing.OpenForTesting

@OpenForTesting
data class MovieWebServiceRequest(
    val apiKey: String,
    val language: String = "en-US",
    val page: Int = 1,
    val region: String = "US"
)
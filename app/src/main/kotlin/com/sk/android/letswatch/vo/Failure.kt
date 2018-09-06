package com.sk.android.letswatch.vo

data class ApiFailure(val statusCode: Int, val statusMessage: String) : Exception(statusMessage)
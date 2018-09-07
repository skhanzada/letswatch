package com.sk.android.letswatch

import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.sk.android.letswatch.movies.MoviesFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    val TAG = MoviesFragment::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun loading() {
        Log.d(TAG, "Loading movies")
    }

    fun failed(message: String) {
        Log.d(
            TAG,
            "Error occurred while processing request: msg: $message"
        )
    }

}
package com.sk.android.letswatch.movies


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sk.android.letswatch.BaseFragment
import com.sk.android.letswatch.BuildConfig
import com.sk.android.letswatch.R
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import com.sk.android.letswatch.vo.Status

class MoviesFragment : BaseFragment(), StateLifecycle<MovieWebServiceResponse> {

    val TAG = MoviesFragment::class.java.simpleName

    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel =
                ViewModelProviders.of(activity!!, viewModelFactory).get(MoviesViewModel::class.java)
        moviesViewModel.movies.observe(this, Observer {
            it?.let { update(it) }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.loadTopRatedMovies(MovieWebServiceRequest(BuildConfig.API_KEY))
    }

    override fun loading() {
        Log.d(TAG, "Loading movies")
    }

    override fun update(state: Resource<MovieWebServiceResponse>) {
        when (state.status) {
            Status.LOADING -> loading()
            Status.SUCCESS -> Log.d(TAG, "${state.data?.results?.size} Movies found")
            Status.ERROR -> failed(state)
        }
    }

    override fun failed(failed: Resource<MovieWebServiceResponse>) {
        Log.d(
            TAG,
            "Error occurred while processing request: msg: ${failed.message}"
        )
    }

}

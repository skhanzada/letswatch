package com.sk.android.letswatch.movies


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sk.android.letswatch.BaseFragment
import com.sk.android.letswatch.R
import com.sk.android.letswatch.data.Movie
import com.sk.android.letswatch.vo.Resource
import com.sk.android.letswatch.vo.Status

class MoviesFragment : BaseFragment() {

    val TAG = MoviesFragment::class.java.simpleName

    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel =
                ViewModelProviders.of(activity!!, viewModelFactory).get(MoviesViewModel::class.java)
        moviesViewModel.topRatedMovies.observe(this, Observer {
            updateState(it)
        })
    }

    fun updateState(it: Resource<List<Movie>>?) {
        when (it?.status) {
            Status.LOADING -> Log.d(TAG, "Loading movies")
            Status.SUCCESS -> Log.d(TAG, "${it.data?.size} Movies found")
            Status.ERROR -> Log.d(TAG, "Oops, something went wrong...")
        }
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
        moviesViewModel.loadTopRatedMovies()
    }
}

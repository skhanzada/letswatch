package com.sk.android.letswatch.movies


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment(), StateLifecycle<MovieWebServiceResponse> {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesViewModel =
                ViewModelProviders.of(activity!!, viewModelFactory).get(MoviesViewModel::class.java)
        moviesViewModel.movies.observe(this, Observer {
            it?.let { update(it) }
        })
        moviesViewModel.loadTopRatedMovies(MovieWebServiceRequest(BuildConfig.API_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
        moviesAdapter = MoviesAdapter()
        moviesRecyclerView.adapter = moviesAdapter
    }

    override fun update(state: Resource<MovieWebServiceResponse>) {
        // TODO: need to display loading spinner and failed message
        when (state.status) {
            Status.LOADING -> loading()
            Status.SUCCESS -> moviesAdapter.update(state.data?.results!!)
            Status.ERROR -> failed(state.message!!)
        }
    }

}

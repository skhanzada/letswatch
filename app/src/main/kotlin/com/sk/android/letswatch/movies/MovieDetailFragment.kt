package com.sk.android.letswatch.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sk.android.letswatch.BaseFragment
import com.sk.android.letswatch.BuildConfig
import com.sk.android.letswatch.R
import com.sk.android.letswatch.data.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*

const val MOVIE_DETAIL_IMAGE_BASE_URL = BuildConfig.IMAGES_BASE_URL + "w185"

class MovieDetailFragment : BaseFragment() {

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(MOVIE)?.let { init(it) }
    }

    fun init(movie: Movie) {
        Glide.with(this).load(MOVIE_DETAIL_IMAGE_BASE_URL + movie.posterPath).into(movieImage)

        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieRating.text = movie.voteAverage.toString()
    }

    companion object {

        private const val MOVIE = "movie"

        fun newInstance(movie: Movie?) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE, movie)
            }
        }
    }

}
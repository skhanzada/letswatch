package com.sk.android.letswatch.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.sk.android.letswatch.BuildConfig
import com.sk.android.letswatch.data.Movie
import kotlinx.android.synthetic.main.movie_card_layout.view.*

const val THUMBNAIL_IMAGE_BASE_URL = BuildConfig.IMAGES_BASE_URL + "w92"

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        Glide.with(itemView.context)
            .load(THUMBNAIL_IMAGE_BASE_URL + movie.posterPath)
            .into(itemView.movieImage)

        itemView.movieTitle.text = movie.title
        itemView.movieOverview.text = movie.overview
        itemView.movieRating.text = movie.voteAverage.toString()
    }

}

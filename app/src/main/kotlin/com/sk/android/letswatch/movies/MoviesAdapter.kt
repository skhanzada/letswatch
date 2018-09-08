package com.sk.android.letswatch.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sk.android.letswatch.R
import com.sk.android.letswatch.data.Movie

class MoviesAdapter(private val moviesViewModel: MoviesViewModel) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.movie_card_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { moviesViewModel.movieDetail.value = movies[position] }
    }

    fun update(movies: List<Movie>) {
        this.movies = movies
        //TODO calculte diffutil and notify adapter
        notifyDataSetChanged()
    }

}
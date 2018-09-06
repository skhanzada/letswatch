package com.sk.android.letswatch.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import java.util.*

@Entity(
    indices = [
        Index("topRated")
    ],
    primaryKeys = ["id"]
)
data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    //TODO: enable it when applying genre filter
//    val genreIds: List<Genre>,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: Date,
    val originalLanguage: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    val adult: Boolean,
    val video: Boolean,
    val topRated: Boolean
) {

    /**
     * Join table entity for movie and genre since its a many-to-many relationship
     */
    @Entity(
        tableName = "movie_genre",
        primaryKeys = ["movieId", "genreId"],
        foreignKeys = [
            ForeignKey(
                entity = Movie::class,
                parentColumns = ["id"],
                childColumns = ["movieId"],
                onDelete = CASCADE
            ),
            ForeignKey(
                entity = Genre::class,
                parentColumns = ["id"],
                childColumns = ["genreId"],
                onDelete = CASCADE
            )
        ]
    )
    data class MovieGenre(val movieId: Int, val genreId: Int)

}
package com.sk.android.letswatch.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sk.android.letswatch.data.Movie

@Dao
interface MovieDao {

    @Query(
        """
        SELECT * FROM Movie
        WHERE topRated = 'true'
        ORDER BY voteCount DESC"""
    )
    fun getTopRatedMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(data: List<Movie>)
}
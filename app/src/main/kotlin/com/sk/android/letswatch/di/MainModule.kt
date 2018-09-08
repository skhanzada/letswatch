package com.sk.android.letswatch.di


import com.sk.android.letswatch.MainActivity
import com.sk.android.letswatch.movies.MovieDetailFragment
import com.sk.android.letswatch.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    internal abstract fun bindMovieDetailFragment(): MovieDetailFragment

}
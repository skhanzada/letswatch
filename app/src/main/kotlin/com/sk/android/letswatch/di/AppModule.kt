package com.sk.android.letswatch.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.sk.android.letswatch.LetsWatchApp
import com.sk.android.letswatch.data.source.local.LetsWatchDatabase
import com.sk.android.letswatch.data.source.local.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [MainModule::class, WebServiceModule::class, ViewModelsModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: LetsWatchApp): Context {
        return application.getApplicationContext()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providesDatabase(context: Context): LetsWatchDatabase {
        return LetsWatchDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providesMovieDao(database: LetsWatchDatabase): MovieDao {
        return database.moveDao()
    }
}
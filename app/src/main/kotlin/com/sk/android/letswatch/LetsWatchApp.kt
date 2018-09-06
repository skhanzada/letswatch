package com.sk.android.letswatch

import com.facebook.stetho.Stetho
import com.sk.android.letswatch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

class LetsWatchApp : DaggerApplication(), HasActivityInjector {

    override fun onCreate() {
        super.onCreate()
        //Enable debugging tool stetho depending on the build
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector<out LetsWatchApp> {
        return DaggerAppComponent.builder().create(this)
    }

}
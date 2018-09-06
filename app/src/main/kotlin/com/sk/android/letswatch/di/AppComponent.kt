package com.sk.android.letswatch.di

import com.sk.android.letswatch.LetsWatchApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class))
interface AppComponent : AndroidInjector<LetsWatchApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LetsWatchApp>()

}

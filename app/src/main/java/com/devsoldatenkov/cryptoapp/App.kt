package com.devsoldatenkov.cryptoapp

import android.app.Application
import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di.DaggerAppComponent
import com.devsoldatenkov.remote.DaggerRemoteComponent
import timber.log.Timber

class App : Application() {
    lateinit var daggerComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        daggerComponent = DaggerAppComponent
            .builder()
            .getContext(applicationContext)
            .remoteProvider(DaggerRemoteComponent.create())
            .build()

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
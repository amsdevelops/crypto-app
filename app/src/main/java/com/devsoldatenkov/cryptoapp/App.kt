package com.devsoldatenkov.cryptoapp

import android.app.Application
import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di.DaggerAppComponent
import com.devsoldatenkov.remote.DaggerRemoteComponent
import timber.log.Timber

open class App : Application() {
    protected var daggerComponent: AppComponent? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    open fun getAppComponent() = daggerComponent ?: DaggerAppComponent
        .builder()
        .getContext(applicationContext)
        .remoteProvider(DaggerRemoteComponent.create())
        .build()

    companion object {
        lateinit var instance: App
            private set
    }
}
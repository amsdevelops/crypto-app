package com.devsoldatenkov.cryptoapp

import android.app.Application
import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().build()

        super.onCreate()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
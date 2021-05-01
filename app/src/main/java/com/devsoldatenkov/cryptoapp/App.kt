package com.devsoldatenkov.cryptoapp

import android.app.Application
import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di.DaggerAppComponent
import com.devsoldatenkov.cryptoapp.di.modules.DomainModule

class App : Application() {
    lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        daggerComponent = DaggerAppComponent.builder()
            .domainModule(DomainModule())
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
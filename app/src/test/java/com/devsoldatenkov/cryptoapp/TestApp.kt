package com.devsoldatenkov.cryptoapp

import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di_test.DaggerTestAppComponent
import com.devsoldatenkov.cryptoapp.di_test.TestModule

class TestApp : App() {

    override fun getAppComponent(): AppComponent = daggerComponent ?: DaggerTestAppComponent
        .builder()
        .testModule(TestModule(applicationContext))
        .build()
}
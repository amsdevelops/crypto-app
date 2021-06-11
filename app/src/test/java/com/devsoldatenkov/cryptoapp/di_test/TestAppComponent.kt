package com.devsoldatenkov.cryptoapp.di_test

import com.devsoldatenkov.cryptoapp.di.AppComponent
import com.devsoldatenkov.cryptoapp.di.modules.DatabaseModule
import com.devsoldatenkov.cryptoapp.di.modules.DomainModule
import com.devsoldatenkov.cryptoapp.integration.HomeFragmentTest
import com.devsoldatenkov.remote.test.RemoteTestModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [TestModule::class, RemoteTestModule::class, DatabaseModule::class, DomainModule::class])
@Singleton
interface TestAppComponent : AppComponent {

    fun inject(homeFragmentTest: HomeFragmentTest)
}
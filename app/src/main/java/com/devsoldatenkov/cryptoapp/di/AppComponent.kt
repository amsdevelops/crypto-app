package com.devsoldatenkov.cryptoapp.di

import com.devsoldatenkov.cryptoapp.view.MainActivity
import com.devsoldatenkov.cryptoapp.di.modules.DomainModule
import com.devsoldatenkov.cryptoapp.di.modules.RemoteModule
import com.devsoldatenkov.cryptoapp.view.viewmodels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}
package com.devsoldatenkov.cryptoapp.di

import android.content.Context
import com.devsoldatenkov.cryptoapp.di.modules.DatabaseModule
import com.devsoldatenkov.cryptoapp.di.modules.DomainModule
import com.devsoldatenkov.cryptoapp.di.modules.RemoteModule
import com.devsoldatenkov.cryptoapp.view.viewmodels.HomeFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance fun getContext(application: Context): Builder
        fun build(): AppComponent
    }
}
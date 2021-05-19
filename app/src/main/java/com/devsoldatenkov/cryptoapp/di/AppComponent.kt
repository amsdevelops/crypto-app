package com.devsoldatenkov.cryptoapp.di

import android.content.Context
import com.devsoldatenkov.cryptoapp.di.modules.DatabaseModule
import com.devsoldatenkov.cryptoapp.di.modules.DomainModule
import com.devsoldatenkov.cryptoapp.view.viewmodels.DetailsFragmentViewModel
import com.devsoldatenkov.cryptoapp.view.viewmodels.FavoritesFragmentViewModel
import com.devsoldatenkov.cryptoapp.view.viewmodels.HomeFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import com.devsoldatenkov.remote.RemoteProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [RemoteProvider::class],
    modules = [
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(detailsFragmentViewModel: DetailsFragmentViewModel)
    fun inject(favoritesFragmentViewModel: FavoritesFragmentViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getContext(application: Context): Builder
        fun remoteProvider(remoteProvider: RemoteProvider): Builder
        fun build(): AppComponent
    }
}
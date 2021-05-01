package com.devsoldatenkov.cryptoapp.di.modules

import com.devsoldatenkov.cryptoapp.domain.Interactor
import com.devsoldatenkov.cryptoapp.remote.CoinCapApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(coinCapApi: CoinCapApi) = Interactor(remote = coinCapApi)
}
package com.devsoldatenkov.remote

interface RemoteProvider {
    fun provideRemote(): CoinCapApi
}
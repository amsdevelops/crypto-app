package com.devsoldatenkov.cryptoapp.di.modules

import android.content.Context
import androidx.room.Room
import com.devsoldatenkov.cryptoapp.data.CoinRepository
import com.devsoldatenkov.cryptoapp.data.db.CoinDatabase
import com.devsoldatenkov.cryptoapp.data.db.dao.CoinDataDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    private const val DATABASE_NAME = "coin_db"
    @Provides
    @Singleton
    fun provideDatabase(context: Context): CoinDataDao =
        Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            DATABASE_NAME
        ).build().coinDataDao()

    @Provides
    fun provideCoinRepository(coinDataDao: CoinDataDao): CoinRepository = CoinRepository(coinDataDao)
}
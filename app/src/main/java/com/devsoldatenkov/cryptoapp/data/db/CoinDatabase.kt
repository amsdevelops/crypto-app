package com.devsoldatenkov.cryptoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devsoldatenkov.cryptoapp.data.db.dao.CoinDataDao
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.data.db.entity.FavoritesCoinData

@Database(entities = [CoinData::class, FavoritesCoinData::class], version = 1, exportSchema = false)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDataDao(): CoinDataDao
}
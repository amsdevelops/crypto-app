package com.devsoldatenkov.cryptoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devsoldatenkov.cryptoapp.data.db.dao.CoinDataDao
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData

@Database(entities = [CoinData::class], version = 1, exportSchema = false)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDataDao(): CoinDataDao
}
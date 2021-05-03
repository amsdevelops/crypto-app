package com.devsoldatenkov.cryptoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import io.reactivex.rxjava3.core.Observable

@Dao
interface CoinDataDao {
    @Query("SELECT * FROM coin_data")
    fun getCachedData(): Observable<List<CoinData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CoinData>)
}
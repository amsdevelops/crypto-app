package com.devsoldatenkov.cryptoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.data.db.entity.FavoritesCoinData
import io.reactivex.rxjava3.core.Observable

@Dao
interface CoinDataDao {
    @Query("SELECT * FROM coin_data")
    fun getCachedData(): Observable<List<CoinData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CoinData>)

    @Query("SELECT * FROM favorites_coin_data")
    fun getFavoritesData(): Observable<List<FavoritesCoinData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoritesCoinData: FavoritesCoinData)
}
package com.devsoldatenkov.cryptoapp.data

import com.devsoldatenkov.cryptoapp.data.db.dao.CoinDataDao
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinDataBasic
import com.devsoldatenkov.cryptoapp.data.db.entity.FavoritesCoinData
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Executors

class CoinRepository(private val coinDataDao: CoinDataDao) {
    fun getCoinsFromCache(): Observable<List<CoinData>> = coinDataDao.getCachedData()
    fun getFavoritesCoins(): Observable<List<FavoritesCoinData>> = coinDataDao.getFavoritesData()

    fun putCoinsToCache(list: List<CoinData>) {
        coinDataDao.insertAll(list)
    }

    fun insertCoinToFavorites(coin: CoinDataBasic) {
        Executors.newSingleThreadExecutor().execute {
            coinDataDao.insert(coin as FavoritesCoinData)
        }
    }
}
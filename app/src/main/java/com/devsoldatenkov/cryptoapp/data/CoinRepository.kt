package com.devsoldatenkov.cryptoapp.data

import com.devsoldatenkov.cryptoapp.data.db.dao.CoinDataDao
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import io.reactivex.rxjava3.core.Observable

class CoinRepository(private val coinDataDao: CoinDataDao) {
    fun getCoinsFromCache(): Observable<List<CoinData>> = coinDataDao.getCachedData()

    fun putCoinsToCache(list: List<CoinData>) {
        coinDataDao.insertAll(list)
    }
}
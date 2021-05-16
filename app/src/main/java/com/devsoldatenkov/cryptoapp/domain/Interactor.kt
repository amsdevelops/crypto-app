package com.devsoldatenkov.cryptoapp.domain

import com.devsoldatenkov.cryptoapp.data.CoinRepository
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.utils.Converters.toCoinData
import com.devsoldatenkov.remote.CoinCapApi
import com.devsoldatenkov.remote.entity.CoinHistoryResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class Interactor(private val remote: CoinCapApi, private val repository: CoinRepository) {
    fun getCoinsFromCache(): Observable<List<CoinData>> = repository.getCoinsFromCache()

    fun getAssetsFromRemote() {
        remote.getAssets()
            .subscribeOn(Schedulers.io())
            .map {
                it.data.map { coinDataDto ->
                    coinDataDto.toCoinData()
                }
            }
            .subscribeBy(
                onError = {
                    Timber.e(it.localizedMessage)
                },
                onNext = {
                    repository.putCoinsToCache(it)
                }
            )
    }

    fun getCoinHistoryFromRemote(id: String, interval: String, start: Long, end: Long): Observable<CoinHistoryResult> =
        remote.getCoinHistory(id, interval, start, end)
            .subscribeOn(Schedulers.io())
}
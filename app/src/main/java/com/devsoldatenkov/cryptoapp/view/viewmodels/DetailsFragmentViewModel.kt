package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.App
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinDataBasic
import com.devsoldatenkov.cryptoapp.domain.Interactor
import com.devsoldatenkov.remote.entity.CoinHistoryResult
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DetailsFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.getAppComponent().inject(this)
    }

    fun getCoinHistory(id: String, interval: String, start: Long, end: Long): Observable<CoinHistoryResult> =
        interactor.getCoinHistoryFromRemote(id, interval, start, end)

    fun insertCoinToFavorites(coin: CoinDataBasic) {
        interactor.insertCoinToFavorites(coin)
    }
}
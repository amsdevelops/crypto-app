package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.App
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.daggerComponent.inject(this)
        getAssetsFromRemote()
    }

    fun getAssetsFromRemote() {
        interactor.getAssetsFromRemote()
    }

    fun getCoinsFromCache(): Observable<List<CoinData>> = interactor.getCoinsFromCache()
}
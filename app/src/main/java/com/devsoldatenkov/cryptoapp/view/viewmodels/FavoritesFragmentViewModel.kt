package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.App
import com.devsoldatenkov.cryptoapp.data.db.entity.FavoritesCoinData
import com.devsoldatenkov.cryptoapp.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FavoritesFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.getAppComponent().inject(this)
    }

    fun getFavoritesCoins(): Observable<List<FavoritesCoinData>> = interactor.getFavoritesCoin()
}
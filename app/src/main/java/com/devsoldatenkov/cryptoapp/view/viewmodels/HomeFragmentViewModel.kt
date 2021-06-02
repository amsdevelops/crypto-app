package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.App
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.domain.Interactor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    var coinDataList: MutableLiveData<List<CoinData>> = MutableLiveData()

    init {
        App.instance.daggerComponent.inject(this)
        getAssetsFromRemote()


        getCoinsFromCache()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { Timber.e(it.localizedMessage) },
                onNext = {
                    coinDataList.postValue(it)
                }
            )
    }

    fun getAssetsFromRemote() {
        interactor.getAssetsFromRemote()
    }

    fun getCoinsFromCache(): Observable<List<CoinData>> = interactor.getCoinsFromCache()
}
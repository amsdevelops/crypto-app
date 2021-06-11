package com.devsoldatenkov.cryptoapp.domain

import com.devsoldatenkov.cryptoapp.entity.AssetsResult
import com.devsoldatenkov.cryptoapp.remote.CoinCapApi
import io.reactivex.rxjava3.core.Observable

class Interactor(val remote: CoinCapApi) {

    fun getAssets(): Observable<AssetsResult> = remote.getAssets()
}
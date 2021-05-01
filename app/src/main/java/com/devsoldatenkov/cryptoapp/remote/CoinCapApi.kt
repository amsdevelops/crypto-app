package com.devsoldatenkov.cryptoapp.remote

import com.devsoldatenkov.cryptoapp.entity.AssetsResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CoinCapApi {
    @GET("assets")
    fun getAssets(): Observable<AssetsResult>
}
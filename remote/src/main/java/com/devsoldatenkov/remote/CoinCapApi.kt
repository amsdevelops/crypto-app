package com.devsoldatenkov.remote

import com.devsoldatenkov.remote.entity.AssetsResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CoinCapApi {
    @GET("assets")
    fun getAssets(): Observable<AssetsResult>
}
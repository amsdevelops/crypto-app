package com.devsoldatenkov.remote

import com.devsoldatenkov.remote.entity.AssetsResult
import com.devsoldatenkov.remote.entity.CoinHistoryResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinCapApi {
    @GET("assets")
    fun getAssets(): Observable<AssetsResult>

    @GET("assets/{id}/history")
    fun getCoinHistory(
        @Path("id") id: String,
        @Query("interval") interval: String,
        @Query("start") start: Long,
        @Query("end") end: Long
    ): Observable<CoinHistoryResult>
}
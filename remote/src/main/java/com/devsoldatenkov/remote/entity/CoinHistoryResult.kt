package com.devsoldatenkov.remote.entity


import com.google.gson.annotations.SerializedName

data class CoinHistoryResult(
    @SerializedName("data")
    val `data`: List<CoinHistoryDto>,
    @SerializedName("timestamp")
    val timestamp: Long
)
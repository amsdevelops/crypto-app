package com.devsoldatenkov.remote.entity


import com.google.gson.annotations.SerializedName

data class AssetsResult(
    @SerializedName("data")
    val `data`: List<CoinDataDto>,
    @SerializedName("timestamp")
    val timestamp: Long
)
package com.devsoldatenkov.cryptoapp.entity


import com.google.gson.annotations.SerializedName

data class AssetsResult(
    @SerializedName("data")
    val `data`: List<CoinData>,
    @SerializedName("timestamp")
    val timestamp: Long
)
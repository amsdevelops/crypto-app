package com.devsoldatenkov.remote.entity


import com.google.gson.annotations.SerializedName

data class CoinHistoryDto(
    @SerializedName("priceUsd")
    val priceUsd: String,
    @SerializedName("time")
    val time: Long
)
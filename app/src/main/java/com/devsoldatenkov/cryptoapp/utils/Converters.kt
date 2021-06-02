package com.devsoldatenkov.cryptoapp.utils

import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.entity.CoinDataDto

object Converters {
    fun CoinDataDto.toCoinData() = CoinData(
        id = id ?: "",
        changePercent24Hr = changePercent24Hr ?: "",
        marketCapUsd = marketCapUsd ?: "",
        maxSupply = maxSupply ?: "",
        name = name ?: "",
        priceUsd = priceUsd ?: "",
        rank = rank ?: "",
        supply = supply ?: "",
        symbol = symbol ?: "",
        volumeUsd24Hr = volumeUsd24Hr ?: "",
        vwap24Hr = vwap24Hr ?: ""
    )
}
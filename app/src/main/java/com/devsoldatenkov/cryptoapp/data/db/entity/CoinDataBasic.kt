package com.devsoldatenkov.cryptoapp.data.db.entity

import android.os.Parcelable

abstract class CoinDataBasic(
    open val cache_id: Int,
    open val id: String,
    open val changePercent24Hr: String,
    open val marketCapUsd: String,
    open val maxSupply: String,
    open val name: String,
    open val priceUsd: String,
    open val rank: String,
    open val supply: String,
    open val symbol: String,
    open val volumeUsd24Hr: String,
    open val vwap24Hr: String
) : Parcelable
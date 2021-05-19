package com.devsoldatenkov.cryptoapp.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorites_coin_data")
data class FavoritesCoinData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cache_id")
    override val cache_id: Int = 0,
    @ColumnInfo(name = "id")
    override val id: String,
    @ColumnInfo(name = "changePercent24Hr")
    override val changePercent24Hr: String,
    @ColumnInfo(name = "marketCapUsd")
    override val marketCapUsd: String,
    @ColumnInfo(name = "maxSupply")
    override val maxSupply: String,
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "priceUsd")
    override val priceUsd: String,
    @ColumnInfo(name = "rank")
    override val rank: String,
    @ColumnInfo(name = "supply")
    override val supply: String,
    @ColumnInfo(name = "symbol")
    override val symbol: String,
    @ColumnInfo(name = "volumeUsd24Hr")
    override val volumeUsd24Hr: String,
    @ColumnInfo(name = "vwap24Hr")
    override val vwap24Hr: String
) : CoinDataBasic(
    cache_id = cache_id,
    id = id,
    changePercent24Hr = changePercent24Hr,
    marketCapUsd = marketCapUsd,
    maxSupply = maxSupply,
    name = name,
    priceUsd = priceUsd,
    rank = rank,
    supply = supply,
    symbol = symbol,
    volumeUsd24Hr = volumeUsd24Hr,
    vwap24Hr = vwap24Hr
), Parcelable
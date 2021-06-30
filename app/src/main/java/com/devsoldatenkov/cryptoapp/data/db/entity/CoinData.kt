package com.devsoldatenkov.cryptoapp.data.db.entity


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "coin_data")
data class CoinData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cache_id")
    val cache_id: Int = 0,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "changePercent24Hr")
    val changePercent24Hr: String,
    @ColumnInfo(name = "marketCapUsd")
    val marketCapUsd: String,
    @ColumnInfo(name = "maxSupply")
    val maxSupply: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "priceUsd")
    val priceUsd: String,
    @ColumnInfo(name = "rank")
    val rank: String,
    @ColumnInfo(name = "supply")
    val supply: String,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "volumeUsd24Hr")
    val volumeUsd24Hr: String,
    @ColumnInfo(name = "vwap24Hr")
    val vwap24Hr: String
) : Parcelable
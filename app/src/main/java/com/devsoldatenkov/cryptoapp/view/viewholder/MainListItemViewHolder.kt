package com.devsoldatenkov.cryptoapp.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.databinding.MainListItemBinding

class MainListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = MainListItemBinding.bind(itemView)
    fun bind(item: CoinData) {
        binding.apply {
            val rankText = "#${item.rank}"
            rankTv.text = rankText
            symbolTv.text = item.symbol
            nameTv.text = item.name

            var dotIndex = item.priceUsd.indexOf(".")
            val priceUSD = item.priceUsd.substring(0, dotIndex + 3)
            "price USD: $priceUSD$".also { priceUsdTv.text = it }

            dotIndex = item.changePercent24Hr.indexOf(".")
            val changePercent = item.changePercent24Hr.substring(0, dotIndex + 3)
            "change: $changePercent%".also { changePercentTv.text = it }
        }
    }
}
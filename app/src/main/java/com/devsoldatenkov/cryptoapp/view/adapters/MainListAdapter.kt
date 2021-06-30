package com.devsoldatenkov.cryptoapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devsoldatenkov.cryptoapp.R
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.view.viewholder.MainListItemViewHolder

class MainListAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<CoinData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MainListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainListItemViewHolder -> {
                holder.bind(items[position])

                holder.itemView.setOnClickListener {
                    itemClickListener.click(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(list: List<CoinData>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun click(film: CoinData)
    }
}
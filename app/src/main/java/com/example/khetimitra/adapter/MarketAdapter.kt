package com.example.khetimitra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khetimitra.R
import com.example.khetimitra.network.CommodityRecord

class MarketAdapter(private var recordList: MutableList<CommodityRecord>) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCommodity: TextView = itemView.findViewById(R.id.tvCommodity)
        val tvMarket: TextView = itemView.findViewById(R.id.tvMarket)
        val tvPrices: TextView = itemView.findViewById(R.id.tvPrices)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_market, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val record = recordList[position]

        val varietyText = if (!record.variety.isNullOrEmpty()) record.variety else "-"
        holder.tvCommodity.text = "${record.commodity ?: "-"} ($varietyText)"

        val marketText = listOf(record.market, record.district, record.state)
            .map { it ?: "-" }
            .joinToString(", ")
        holder.tvMarket.text = marketText

        val minPrice = record.min_price?.toString() ?: "-"
        val maxPrice = record.max_price?.toString() ?: "-"
        val modalPrice = record.modal_price?.toString() ?: "-"
        holder.tvPrices.text = "Min: ₹$minPrice | Max: ₹$maxPrice | Modal: ₹$modalPrice"

        holder.tvDate.text = "Date: ${record.arrival_date ?: "-"}"
    }

    override fun getItemCount(): Int = recordList.size

    fun updateData(newList: MutableList<CommodityRecord>) {
        recordList = newList
        notifyDataSetChanged()
    }
}

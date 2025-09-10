package com.example.khetimitra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khetimitra.R
import com.example.khetimitra.model.MandiResponse

class MarketAdapter(
    private var records: MutableList<MandiResponse.Record>
) : RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMarket: TextView = view.findViewById(R.id.tvMarket)
        val tvCommodity: TextView = view.findViewById(R.id.tvCommodity)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_market_price, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = records.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.tvMarket.text = "${record.market}, ${record.district}, ${record.state}"
        holder.tvCommodity.text = "${record.commodity} (${record.variety ?: "-"})"
        holder.tvPrice.text = "₹${record.modal_price ?: "-"}"
    }

    fun updateData(newList: MutableList<MandiResponse.Record>) {
        records = newList
        notifyDataSetChanged()
    }
}

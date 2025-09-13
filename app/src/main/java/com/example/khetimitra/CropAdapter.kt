package com.example.khetimitra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CropAdapter(private var crops: List<Crop>) : RecyclerView.Adapter<CropAdapter.CropViewHolder>() {

    class CropViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCropName: TextView = itemView.findViewById(R.id.tvCropName)
        val tvCropInfo: TextView = itemView.findViewById(R.id.tvCropInfo)
        val tvCropPrice: TextView = itemView.findViewById(R.id.tvCropPrice)
        val tvHarvestTime: TextView = itemView.findViewById(R.id.tvHarvestTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crop, parent, false)
        return CropViewHolder(view)
    }

    override fun onBindViewHolder(holder: CropViewHolder, position: Int) {
        val crop = crops[position]
        holder.tvCropName.text = crop.name
        holder.tvCropInfo.text = crop.how_to_cultivate
        holder.tvCropPrice.text = "Price: ${crop.price_info}"
        holder.tvHarvestTime.text = "Harvest Time: ${crop.harvest_time}"
    }

    override fun getItemCount(): Int = crops.size

    fun updateCrops(newCrops: List<Crop>) {
        crops = newCrops
        notifyDataSetChanged()
    }
}

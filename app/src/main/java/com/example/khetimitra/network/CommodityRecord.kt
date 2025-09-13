package com.example.khetimitra.network

data class CommodityRecord(
    val state: String,
    val district: String,
    val commodity: String,
    val variety: String,
    val arrival_date: String,
    val min_price: String,
    val max_price: String,
    val modal_price: String,
    val market: String
)


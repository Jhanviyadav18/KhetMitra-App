package com.example.khetimitra.model

data class MandiResponse(
    val records: List<Record>
) {
    data class Record(
        val state: String?,
        val district: String?,
        val market: String?,
        val commodity: String,
        val variety: String?,
        val min_price: String?,
        val max_price: String?,
        val modal_price: String?
    )
}

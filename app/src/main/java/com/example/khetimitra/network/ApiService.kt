package com.example.khetimitra.network

import retrofit2.http.GET
import retrofit2.http.Query

data class ApiResponse(
    val records: List<CommodityRecord>
)

interface ApiService {
    @GET("resource/35985678-0d79-46b4-9ed6-6f13308a1d24")
    suspend fun getCommodityPrices(
        @Query("api-key") apiKey: String = "YOUR_API_KEY",
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10
    ): ApiResponse
}

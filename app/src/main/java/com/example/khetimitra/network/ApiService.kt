package com.example.khetimitra.network

import com.example.khetimitra.model.MandiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070") // ✅ Mandi Prices resource ID
    fun getMandiPrices(
        @Query("api-key") apiKey: String,
        @Query("format") format: String,
        @Query("limit") limit: Int
    ): Call<MandiResponse>
}

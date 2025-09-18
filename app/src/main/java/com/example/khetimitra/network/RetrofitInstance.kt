package com.example.khetimitra.network

import com.example.khetimitra.SensorModels.SensorApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// --- Retrofit API Interface ---
interface SensorApi {
    @GET("sensor/latest") // Replace with your actual endpoint
    fun getLatestSensorData(): Call<SensorApiResponse>
}

// --- Retrofit Instance Singleton ---
object RetrofitInstance {

    private const val BASE_URL = "https://your-api-base-url.com/" // Replace with your API base URL

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SensorApi by lazy {
        retrofit.create(SensorApi::class.java)
    }
}

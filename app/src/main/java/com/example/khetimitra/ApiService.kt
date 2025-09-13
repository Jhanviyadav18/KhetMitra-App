package com.example.khetimitra

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("crops/all")
    fun getAllCrops(): Call<List<Crop>>
}

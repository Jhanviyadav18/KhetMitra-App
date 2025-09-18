package com.example.khetimitra.network

import com.example.khetimitra.SensorModels.SensorApiResponse
import com.example.khetimitra.model.Crop
import com.example.khetimitra.model.Feedback
import com.example.khetimitra.model.HelpRequest
import com.example.khetimitra.model.User
import retrofit2.Call
import retrofit2.http.*

interface KhetMitraApi {

    @GET("api/crops/all")
    fun getAllCrops(): Call<List<Crop>>

    @POST("api/feedback/submit")
    fun submitFeedback(@Body feedback: Feedback): Call<Feedback>

    @POST("api/help/submit")
    fun submitHelpRequest(@Body helpRequest: HelpRequest): Call<HelpRequest>

    @POST("api/user/register")
    fun registerUser(@Body user: User): Call<User>

    @POST("api/user/login")
    fun loginUser(@Body user: User): Call<User>

    @PUT("api/user/edit")
    fun editUserProfile(@Body user: User): Call<User>

    @GET("sensor/latest")
    fun getLatestSensorData(): Call<SensorApiResponse>
}

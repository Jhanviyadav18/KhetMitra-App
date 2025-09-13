package com.example.khetimitra.network

import com.example.khetimitra.model.Crop
import com.example.khetimitra.model.Feedback
import com.example.khetimitra.model.HelpRequest
import com.example.khetimitra.model.User
import retrofit2.Call
import retrofit2.http.*

interface KhetMitraApi {

    // Get all crops
    @GET("api/crops/all")
    fun getAllCrops(): Call<List<Crop>>

    // Submit Feedback
    @POST("api/feedback/submit")
    fun submitFeedback(@Body feedback: Feedback): Call<Feedback>

    // Submit Help Request
    @POST("api/help/submit")
    fun submitHelpRequest(@Body helpRequest: HelpRequest): Call<HelpRequest>

    // Register User
    @POST("api/user/register")
    fun registerUser(@Body user: User): Call<User>

    // Login User
    @POST("api/user/login")
    fun loginUser(@Body user: User): Call<User>

    // Update Profile
    @PUT("api/user/edit")
    fun editUserProfile(@Body user: User): Call<User>
}

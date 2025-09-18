package com.example.khetmitra

data class SensorData(
    val soilTemp: Float,
    val soilMoist: Float,
    val soilPH: Float,
    val nitrogen: Int,
    val phosphorus: Int,
    val potassium: Int,
    val bmpTemp: Float,
    val pressure: Float,
    val altitude: Float,
    val ds18b20Temp: Float,
    val rain: Int,
    val ldr: Int,
    val button: Int,
    val voltage: Float,
    val createdAt: String,
    val _id: String
)

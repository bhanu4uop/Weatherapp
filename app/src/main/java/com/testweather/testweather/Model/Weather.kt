package com.testweather.testweather.model


import com.google.gson.annotations.SerializedName
/**
 * Creates by Bhanu Chander on 11-04-2023.
 */

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
package com.testweather.testweather.Model
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


/**
 * Creates by Bhanu Chander on 11-04-2023.
 */


data class UserModel(
    @SerializedName("shortbio")
    @Expose
    var shortbio: String,
    @SerializedName("username")
    @Expose
    var username: String
)
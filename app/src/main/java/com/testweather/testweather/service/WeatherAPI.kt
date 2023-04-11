package com.testweather.testweather.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import testweather.testweather.model.WeatherModel

/**
 * Creates by Bhanu Chander on 11-04-2023.
 */

interface WeatherAPI {

    @GET("data/2.5/weather?&units=metric&APPID=cb60e3e050c820a30788526d07e14734")
    fun getData(@Query("q") cityName: String): Single<WeatherModel>


}
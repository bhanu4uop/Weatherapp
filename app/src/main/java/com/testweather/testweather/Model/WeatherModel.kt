package testweather.testweather.model

import com.testweather.testweather.model.*

/**
 * Creates by Bhanu Chander on 11-04-2023.
 */

data class WeatherModel(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
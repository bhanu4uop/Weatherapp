package com.testweather.testweather.Views

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.testweather.testweather.R
import com.testweather.testweather.databinding.ActivityHomeBinding
import com.testweather.testweather.viewmodel.MainViewModel

/**
 * Creates by Bhanu Chander on 11-04-2023.
 */

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var viewmodel: MainViewModel
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()
        viewmodel = ViewModelProviders.of(this)[MainViewModel::class.java]
        val cName = GET.getString("cityName", "Chicago")?.toLowerCase()
        binding.edtCityName.setText(cName)
        viewmodel.refreshData(cName!!)

        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.llData.visibility = View.GONE
            binding.tvError.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE

            val cityName = GET.getString("cityName", cName)?.toLowerCase()
            binding.edtCityName.setText(cityName)
            viewmodel.refreshData(cityName!!)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.getct.setOnClickListener {
            if(binding.edtCityName.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter city Name", Toast.LENGTH_SHORT).show()

            }else if(binding.edtCount.text.toString().isEmpty()){

                Toast.makeText(this, "Please Country Code", Toast.LENGTH_SHORT).show()

            }else {
                val cityName =
                    binding.edtCityName.text.toString() + "," + binding.edtCount.text.toString()
                SET.putString("cityName", cityName)
                SET.apply()

                viewmodel.refreshData(cityName)
                getLiveData()
                Log.i("TAG", "onCreate: $cityName")
            }
        }

        binding.getcountyr.setOnClickListener {
            if(binding.edtCityName.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter city Name", Toast.LENGTH_SHORT).show()

            }else if(binding.edtCount.text.toString().isEmpty()){

                Toast.makeText(this, "Please Country Code", Toast.LENGTH_SHORT).show()

            }else if(binding.edtState.text.toString().isEmpty()){

                Toast.makeText(this, "Please State Code", Toast.LENGTH_SHORT).show()

            }else {
                val cityName =
                    binding.edtCityName.text.toString() + "," + binding.edtCount.text.toString() + "," + binding.edtState.text.toString()
                SET.putString("cityName", cityName)
                SET.apply()

                viewmodel.refreshData(cityName)
                getLiveData()
                Log.i("TAG", "onCreate: $cityName")

                }

        }


        binding.imgSearchCity.setOnClickListener {
            val cityName = binding.edtCityName.text.toString()
            SET.putString("cityName", cityName)
            SET.apply()

            viewmodel.refreshData(cityName)
            getLiveData()
            Log.i("TAG", "onCreate: $cityName")

        }


    }


    private fun getLiveData() {


        viewmodel.weather_data.observe(this,  androidx.lifecycle.Observer {
                data ->
            data.let {
                binding.llData.visibility = View.VISIBLE
                binding.tvCityCode.text = data.sys.country.toString()
                binding.tvCityName.text = data.name
                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + data.weather[0].icon + "@2x.png")
                    .into(binding.imgWeatherPictures)
                binding.tvDegree.text = data.main.temp.toString() + "°C"
                binding.tvHumidity.text = data.main.humidity.toString() + "%"
                binding.tvWindSpeed.text = data.wind.speed.toString()
                binding.tvLat.text = data.coord.lat.toString()
                binding.tvLon.text = data.coord.lon.toString()


            }

        })



        viewmodel.weather_error.observe(this, androidx.lifecycle.Observer {
                error->
            error.let {


                if (error) {
                    binding.tvError.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                    binding.llData.visibility = View.GONE
                } else {
                    binding.tvError.visibility = View.GONE
                }


            }
        })



        viewmodel.weather_loading.observe(this, androidx.lifecycle.Observer {
                loading->
            loading.let {
                if (loading) {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.llData.visibility = View.GONE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }

            }

        })





    }
}
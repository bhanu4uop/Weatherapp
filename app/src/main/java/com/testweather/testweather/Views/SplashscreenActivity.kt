package com.testweather.testweather.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.testweather.testweather.Network.CheckNetworkConnection
import com.testweather.testweather.R

/**
 * Creates by Bhanu Chander on 11-04-2023.
 */
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        checkNetworkConnection = CheckNetworkConnection(application)

        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                Handler(Looper.getMainLooper()).postDelayed({

                        val intent = Intent(this, HomeActivity::class.java)
                        overridePendingTransition(R.anim.bottom_up, R.anim.nothing)
                        startActivity(intent)
                        finish()


                }, 3000)

            } else {
                Toast.makeText(this@SplashscreenActivity,
                    "Please check internet connection..!",
                    Toast.LENGTH_LONG).show()

            }
        }


    }
}
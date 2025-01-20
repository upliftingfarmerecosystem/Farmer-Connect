package com.example.farmerconnect

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class TransportActivity : AppCompatActivity() {

    private lateinit var btnGetLocation: Button
    private lateinit var etSearchLocation: EditText
    private lateinit var weatherIcon: ImageView
    private lateinit var tvCityName: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWeatherDescription: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val apiKey = "14e6435f0ef3bdec85fd794fc46743d0" // Replace with your weather API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport)

        // Initialize UI components
        btnGetLocation = findViewById(R.id.btnGetLocation)
        etSearchLocation = findViewById(R.id.etSearchLocation)
        weatherIcon = findViewById(R.id.weatherIcon)
        tvCityName = findViewById(R.id.tvCityName)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWeatherDescription = findViewById(R.id.tvWeatherDescription)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWindSpeed = findViewById(R.id.tvWindSpeed)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Handle "Use Current Location" button click
        btnGetLocation.setOnClickListener {
            if (checkLocationPermission()) {
                getCurrentLocation()
            } else {
                requestLocationPermission()
            }
        }

        // Handle manual location input
        etSearchLocation.setOnEditorActionListener { _, _, _ ->
            val location = etSearchLocation.text.toString().trim()
            if (location.isNotEmpty()) {
                fetchWeatherForLocation(location)
            } else {
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                // Fetch city name using reverse geocoding
                val cityName = getCityNameFromCoordinates(latitude, longitude)
                tvCityName.text = cityName

                // Fetch weather data for the location
                fetchWeatherForCoordinates(latitude, longitude)
            } else {
                Toast.makeText(this, "Unable to fetch location", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to get location", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCityNameFromCoordinates(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                addresses[0].locality ?: "Unknown City"
            } else {
                "Unknown City"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "Unknown City"
        }
    }

    private fun fetchWeatherForCoordinates(latitude: Double, longitude: Double) {
        val url =
            "https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$apiKey&units=metric"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@TransportActivity,
                        "Failed to fetch weather data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        parseWeatherData(responseData)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@TransportActivity,
                            "Failed to fetch weather for location",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun fetchWeatherForLocation(location: String) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$location&appid=$apiKey&units=metric"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@TransportActivity,
                        "Failed to fetch weather data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        parseWeatherData(responseData)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@TransportActivity,
                            "Failed to fetch weather for location",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun parseWeatherData(responseData: String) {
        val jsonObject = JSONObject(responseData)
        val main = jsonObject.getJSONObject("main")
        val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
        val wind = jsonObject.getJSONObject("wind")

        val cityName = jsonObject.getString("name") // Extract city name
        val temperature = main.getString("temp") + "°C"
        val description = weather.getString("description").capitalize()
        val humidity = main.getString("humidity") + "%"
        val windSpeed = wind.getString("speed") + " km/h"

        runOnUiThread {
            displayWeatherData(cityName, temperature, description, humidity, windSpeed)
        }
    }

    private fun displayWeatherData(
        cityName: String,
        temperature: String,
        description: String,
        humidity: String,
        windSpeed: String
    ) {
        tvCityName.text = cityName // Display city name
        tvTemperature.text = temperature
        tvWeatherDescription.text = description
        tvHumidity.text = "Humidity: $humidity"
        tvWindSpeed.text = "Wind: $windSpeed"

        // Update weather icon dynamically (use appropriate resources based on API response)
        weatherIcon.setImageResource(R.drawable.ic_weather_sunny) // Replace with actual logic for icons
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}

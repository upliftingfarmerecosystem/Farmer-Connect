package com.example.farmerconnect

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FertilizersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fertilizers) // Ensure this matches your XML file name

        // Initialize buttons
        val straightFertilizerButton: Button = findViewById(R.id.straightFertilizersButton)
        val multinutrientFertilizerButton: Button = findViewById(R.id.multinutrientFertilizersButton)
        val inorganicFertilizerButton: Button = findViewById(R.id.inorganicFertilizersButton)
        val organicFertilizerButton: Button = findViewById(R.id.organicFertilizersButton)
        val nitrogenFertilizerButton: Button = findViewById(R.id.nitrogenFertilizersButton)
        val phosphorusFertilizerButton: Button = findViewById(R.id.phosphorusFertilizersButton)
        val potassiumFertilizerButton: Button = findViewById(R.id.potassiumFertilizersButton)

        val micronutrientFertilizerButton: Button = findViewById(R.id.micronutrientFertilizersButton)
        val liquidFertilizerButton: Button = findViewById(R.id.liquidFertilizersButton)

        // Set click listeners
        straightFertilizerButton.setOnClickListener { addToCart("Straight Fertilizer") }
        multinutrientFertilizerButton.setOnClickListener { addToCart("Multinutrient Fertilizer") }
        inorganicFertilizerButton.setOnClickListener { addToCart("Inorganic Fertilizer") }
        organicFertilizerButton.setOnClickListener { addToCart("Organic Fertilizer") }
        nitrogenFertilizerButton.setOnClickListener { addToCart("Nitrogen Fertilizer") }
        phosphorusFertilizerButton.setOnClickListener { addToCart("Phosphorus Fertilizer") }
        potassiumFertilizerButton.setOnClickListener { addToCart("Potassium Fertilizer") }
        micronutrientFertilizerButton.setOnClickListener { addToCart("Micronutrient Fertilizer") }
        liquidFertilizerButton.setOnClickListener { addToCart("Liquid Fertilizer") }
    }

    private fun addToCart(item: String) {
        // Show a Toast when an item is added to the cart
        Toast.makeText(this, "$item added to cart", Toast.LENGTH_SHORT).show()
    }
}

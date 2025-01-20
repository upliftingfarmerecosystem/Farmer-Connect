package com.example.farmerconnect

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LeaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lease)

        // Initialize Buttons
        val tractorRentButton = findViewById<Button>(R.id.tractorRentButton)
        val sprinklerRentButton = findViewById<Button>(R.id.sprinklerRentButton)
        val dripRentButton = findViewById<Button>(R.id.dripRentButton)
        val landRentButton = findViewById<Button>(R.id.landRentButton)

        // Set OnClickListener for Tractor Rent Button
        tractorRentButton.setOnClickListener {
            Toast.makeText(this, "Tractor added to cart", Toast.LENGTH_SHORT).show()
        }

        // Set OnClickListener for Sprinkler Rent Button
        sprinklerRentButton.setOnClickListener {
            Toast.makeText(this, "Sprinklers added to cart", Toast.LENGTH_SHORT).show()
        }

        // Set OnClickListener for Drip System Rent Button
        dripRentButton.setOnClickListener {
            Toast.makeText(this, "Drip Systems added to cart", Toast.LENGTH_SHORT).show()
        }

        // Set OnClickListener for Land Rent Button
        landRentButton.setOnClickListener {
            Toast.makeText(this, "Land added to cart", Toast.LENGTH_SHORT).show()
        }
    }
}

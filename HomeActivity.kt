package com.example.farmerconnect

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Views
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)
        val imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        val customerSupportButton = findViewById<Button>(R.id.button)

        // Set OnClickListener for ImageButton4
        imageButton4.setOnClickListener {
            val intent = Intent(this, RentLeasePaymentActivity::class.java) // Replace with your target activity
            startActivity(intent)
        }

        // Set OnClickListener for ImageButton5
        imageButton5.setOnClickListener {
            val intent = Intent(this, RentLeasePaymentActivity1::class.java) // Replace with your target activity
            startActivity(intent)
        }

        // Set OnClickListener for ImageButton7
        imageButton7.setOnClickListener {
            val intent = Intent(this, RentLeasePaymentActivity2::class.java) // Replace with your target activity
            startActivity(intent)
        }

        // Set OnClickListener for Customer Support Button
        customerSupportButton.setOnClickListener {
            val intent = Intent(this, CustomerSupportActivity::class.java) // Replace with your target activity
            startActivity(intent)
        }
    }
}

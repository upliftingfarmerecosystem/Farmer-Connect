package com.example.farmerconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RentLeasePaymentActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent_lease_payment)

        // Initialize Buttons
        val btnRent = findViewById<Button>(R.id.btnRent)
        val btnLease = findViewById<Button>(R.id.btnLease)
        val btnPayment = findViewById<Button>(R.id.btnPayment)
        val btnTransactions = findViewById<Button>(R.id.btnTransactions)
        val btnCrops = findViewById<Button>(R.id.btnCrops)
        val btnFertilizers = findViewById<Button>(R.id.btnFertilizers)


        // Handle Button Clicks
        btnRent.setOnClickListener {
            val intent = Intent(this, RentActivity::class.java)
            startActivity(intent)
        }

        btnLease.setOnClickListener {
            val intent = Intent(this, LeaseActivity::class.java)
            startActivity(intent)
        }

        btnPayment.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        btnTransactions.setOnClickListener {
            val intent = Intent(this, TransportActivity::class.java)
            startActivity(intent)
        }

        btnCrops.setOnClickListener {
            val intent = Intent(this, CropsActivity::class.java)
            startActivity(intent)
        }

        btnFertilizers.setOnClickListener {
            val intent = Intent(this, FertilizersActivity::class.java)
            startActivity(intent)


        }
    }
}

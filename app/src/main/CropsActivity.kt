package com.example.farmerconnect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CropsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crops)

        // Initialize buttons
        val oatsAddButton: Button = findViewById(R.id.oatsAddButton)
        val riceAddButton: Button = findViewById(R.id.riceAddButton)
        val wheatAddButton: Button = findViewById(R.id.wheatAddButton)
        val maizeAddButton: Button = findViewById(R.id.maizeAddButton)
        val sorghumAddButton: Button = findViewById(R.id.sorghumAddButton)
        val ragiAddButton: Button = findViewById(R.id.ragiAddButton)
        val tomatoAddButton: Button = findViewById(R.id.tomatoAddButton)
        val carrotAddButton: Button = findViewById(R.id.carrotAddButton)
        val capsicumAddButton: Button = findViewById(R.id.capsicumAddButton)
        val brinjalAddButton: Button = findViewById(R.id.brinjalAddButton)
        val radishAddButton: Button = findViewById(R.id.radishAddButton)
        val greenChiliAddButton: Button = findViewById(R.id.greenChiliAddButton)
        val onionAddButton: Button = findViewById(R.id.onionAddButton)
        val garlicAddButton: Button = findViewById(R.id.garlicAddButton)
        val bananaAddButton: Button = findViewById(R.id.bananaAddButton)

        // Set click listeners for all buttons
        oatsAddButton.setOnClickListener { addToCart("Oats") }
        riceAddButton.setOnClickListener { addToCart("Rice") }
        wheatAddButton.setOnClickListener { addToCart("Wheat") }
        maizeAddButton.setOnClickListener { addToCart("Maize") }
        sorghumAddButton.setOnClickListener { addToCart("Sorghum") }
        ragiAddButton.setOnClickListener { addToCart("Ragi") }
        tomatoAddButton.setOnClickListener { addToCart("Tomato") }
        carrotAddButton.setOnClickListener { addToCart("Carrot") }
        capsicumAddButton.setOnClickListener { addToCart("Capsicum") }
        brinjalAddButton.setOnClickListener { addToCart("Brinjal") }
        radishAddButton.setOnClickListener { addToCart("Radish") }
        greenChiliAddButton.setOnClickListener { addToCart("Green Chili") }
        onionAddButton.setOnClickListener { addToCart("Onion") }
        garlicAddButton.setOnClickListener { addToCart("Garlic") }
        bananaAddButton.setOnClickListener { addToCart("Banana") }
    }

    private fun addToCart(item: String) {
        // Show a Toast when an item is added to the cart
        Toast.makeText(this, "$item added to cart", Toast.LENGTH_SHORT).show()
    }
}

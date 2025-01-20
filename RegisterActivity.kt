package com.example.farmerconnect

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etAadharNumber = findViewById<EditText>(R.id.etAadharNumber)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val aadharNumber = etAadharNumber.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()

            // Perform registration logic here
            // After registration, go to the login page
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

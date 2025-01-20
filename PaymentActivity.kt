package com.example.farmerconnect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var edtAmount: EditText
    private lateinit var edtUpi: EditText
    private lateinit var edtName: EditText
    private lateinit var edtDescription: EditText
    private lateinit var btnMakePayment: Button
    private lateinit var tvTransactionDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Initialize UI components
        edtAmount = findViewById(R.id.idEdtAmount)
        edtUpi = findViewById(R.id.idEdtUpi)
        edtName = findViewById(R.id.idEdtName)
        edtDescription = findViewById(R.id.idEdtDescription)
        btnMakePayment = findViewById(R.id.idBtnMakePayment)
        tvTransactionDetails = findViewById(R.id.idTVTransactionDetails)

        // Handle button click
        btnMakePayment.setOnClickListener {
            val amount = edtAmount.text.toString().trim()
            val upiId = edtUpi.text.toString().trim()
            val name = edtName.text.toString().trim()
            val description = edtDescription.text.toString().trim()

            if (amount.isEmpty() || upiId.isEmpty() || name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                makePayment(amount, upiId, name, description)
            }
        }
    }

    private fun makePayment(amount: String, upiId: String, name: String, description: String) {
        val uri = Uri.Builder()
            .scheme("upi")
            .authority("pay")
            .appendQueryParameter("pa", upiId) // UPI ID
            .appendQueryParameter("pn", name) // Payee name
            .appendQueryParameter("tn", description) // Transaction note
            .appendQueryParameter("am", amount) // Amount
            .appendQueryParameter("cu", "INR") // Currency
            .build()

        Log.d("UPI_URL",uri.toString())

        val upiIntent = Intent(Intent.ACTION_VIEW)
        upiIntent.data = uri

        // Launch the UPI app
        val chooser = Intent.createChooser(upiIntent, "Pay with")
        if (upiIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(chooser, 1)
        } else {
            Toast.makeText(this, "No UPI app found. Please install one to continue.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val response = data?.getStringExtra("response")
            if (response == null) {
                tvTransactionDetails.text = "Transaction failed or canceled"
                tvTransactionDetails.visibility = TextView.VISIBLE
            } else {
                handleUPIResponse(response)
            }
        }
    }

    private fun handleUPIResponse(response: String) {
        val responseMap = response.split("&").associate {
            val (key, value) = it.split("=").map { it.trim() }
            key to value
        }

        val status = responseMap["Status"] ?: "UNKNOWN"
        val txnId = responseMap["txnId"] ?: "N/A"
        val responseMessage = when (status.uppercase()) {
            "SUCCESS" -> "Transaction successful! Transaction ID: $txnId"
            "FAILURE" -> "Transaction failed."
            else -> "Transaction canceled."
        }

        tvTransactionDetails.text = responseMessage
        tvTransactionDetails.visibility = TextView.VISIBLE
    }
}

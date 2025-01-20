package com.example.farmerconnect

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CustomerSupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_support)

        // Initialize Buttons and TextViews
        val buttonsAndTextViews = listOf(
            Pair(findViewById<Button>(R.id.btnGeneralQuery), findViewById<TextView>(R.id.tvGeneralQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnTechnicalSupport), findViewById<TextView>(R.id.tvTechnicalSupportAnswer)),
            Pair(findViewById<Button>(R.id.btnRentQuery), findViewById<TextView>(R.id.tvRentQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnLeaseQuery), findViewById<TextView>(R.id.tvLeaseQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnPaymentQuery), findViewById<TextView>(R.id.tvPaymentQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnMandiQuery), findViewById<TextView>(R.id.tvMandiQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnCropQuery), findViewById<TextView>(R.id.tvCropQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnFertilizerQuery), findViewById<TextView>(R.id.tvFertilizerQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnAccountQuery), findViewById<TextView>(R.id.tvAccountQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnResetPasswordQuery), findViewById<TextView>(R.id.tvResetPasswordQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnAppIssueQuery), findViewById<TextView>(R.id.tvAppIssueQueryAnswer)),
            Pair(findViewById<Button>(R.id.btnContactSupportQuery), findViewById<TextView>(R.id.tvContactSupportQueryAnswer))
        )

        buttonsAndTextViews.forEach { (button, textView) ->
            button.setOnClickListener {
                hideAllTextViews(buttonsAndTextViews)
                textView.visibility = View.VISIBLE
            }
        }
    }

    // Helper function to hide all TextViews
    private fun hideAllTextViews(pairs: List<Pair<Button, TextView>>) {
        pairs.forEach { (_, textView) ->
            textView.visibility = View.GONE
        }
    }
}

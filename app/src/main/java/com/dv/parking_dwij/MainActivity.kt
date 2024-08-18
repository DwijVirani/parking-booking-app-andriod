package com.dv.parking_dwij

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dv.parking_dwij.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var receiptList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        this.binding.btnPayNow.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.menu_item_history -> {
                val historyIntent = Intent(this@MainActivity, ReceiptHistory::class.java)

                historyIntent.putExtra("RECEIPT_HISTORY", receiptList.toTypedArray())
                startActivity(historyIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_pay_now ->this.calculateParkingCharges()
        }
    }

    private fun calculateParkingCharges() {
        val hours = this.binding.etHours.text.toString()
        val licensePlate = this.binding.etLicensePlate.text.toString()

        if(hours.isNullOrEmpty()) {
            this.binding.etHours.error = "This field is required"
            this.binding.tvError.text = "Error: All fields are required"
        } else if(licensePlate.isNullOrEmpty()) {
            this.binding.etLicensePlate.error = "This field is required"
            this.binding.tvError.text = "Error: All fields are required"
        } else {
            binding.tvError.text = ""
            var parkingRate = 0.0
            var parkingLot = ""

            val selectedRadioButtonID = this.binding.rgrpParkingLot.checkedRadioButtonId
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonID)
            val selectedParkingLot = selectedRadioButton.text.toString()
            when(selectedParkingLot) {
                "Kipling \nRate: $3/hr" -> {
                    parkingRate = 3.0
                    parkingLot = "Kipling"
                }
                "Casa Loma \nRate: $4.5/hr" -> {
                    parkingRate = 4.5
                    parkingLot = "Casa Loma"
                }
            }
            val hoursInt = hours.toFloat()
            val parkingCharge = parkingRate * hoursInt

            val output = "$parkingLot\nLicense Plate: $licensePlate\nHours: $hours\nTotal Paid: $parkingCharge"
            binding.tvOutputHeader.text = "Parking Charges"
            binding.tvOutput.text = output

            binding.etHours.text.clear()
            binding.etLicensePlate.text.clear()
            receiptList.add("$parkingLot; $licensePlate; $hours; $parkingCharge")
        }
    }
}
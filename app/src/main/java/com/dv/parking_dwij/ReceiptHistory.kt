package com.dv.parking_dwij

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dv.parking_dwij.databinding.ActivityMainBinding
import com.dv.parking_dwij.databinding.ActivityReceiptHistoryBinding

class ReceiptHistory : AppCompatActivity() {
    private lateinit var adapter: ReceiptAdapter
    private lateinit var binding: ActivityReceiptHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val receivedData = intent.getStringArrayExtra("RECEIPT_HISTORY")
        var receiptList:MutableList<String> = mutableListOf()

        if (receivedData != null) {
            receiptList = receivedData.toMutableList()
        }


        this.adapter = ReceiptAdapter(receiptList)
        binding.rv.adapter = adapter

        binding.rv.layoutManager = LinearLayoutManager(this)

        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.menu_parking_form -> {
                val formIntent = Intent(this@ReceiptHistory, MainActivity::class.java)
                startActivity(formIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
package com.dv.parking_dwij

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReceiptAdapter(var receipts: List<String>): RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {
    inner class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_receipt, parent, false)
        return ReceiptViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return receipts.size
    }

    // In a single row, what data goes in the <TextView>?
    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        // 1. Get the TextView from the row layout
        val tvLabel = holder.itemView.findViewById<TextView>(R.id.tvRowLine1)
        // 2. Set its value
        tvLabel.text = "${receipts.get(position)}"
    }
}
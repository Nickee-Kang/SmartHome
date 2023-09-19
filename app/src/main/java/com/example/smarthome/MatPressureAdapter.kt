package com.example.smarthome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class MatPressureAdapter(private var timestampList: MutableList<String>): RecyclerView.Adapter<MatPressureAdapter.MatPressureViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatPressureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mat_pressure_list_item, parent, false)

        return MatPressureViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatPressureViewHolder, position: Int) {
        val timestamp = timestampList[position]
        holder.bind(timestamp)
    }

    override fun getItemCount() = timestampList.size

    fun updateData(newTimestampList: List<String>) {
        timestampList = newTimestampList.toMutableList()
        notifyDataSetChanged()
    }

    inner class MatPressureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timestampTextView: TextView = itemView.findViewById(R.id.matPressureTimestamp)
        fun bind(timestamp: String){
            timestampTextView.text = formatTimestamp(timestamp)
        }

        private fun formatTimestamp(timestamp: String): String {
            // Assuming `timestamp` is in the format "yyyy-MM-dd HH:mm:ss"
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())

            val date = inputFormat.parse(timestamp)
            return outputFormat.format(date)
        }
    }
}
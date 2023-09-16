package com.example.smarthome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class LaserTripwireAdapter(private var timestampList: MutableList<Timestamp>): RecyclerView.Adapter<LaserTripwireAdapter.LaserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.laser_list_item, parent, false)
        return LaserViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaserViewHolder, position: Int) {
        val timestamp = timestampList[position]
        holder.bind(timestamp)
    }

    override fun getItemCount() = timestampList.size

    fun updateData(newTimestampList: List<Timestamp>) {
        timestampList = newTimestampList.toMutableList()
        notifyDataSetChanged()
    }

    inner class LaserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timestampTextView: TextView = itemView.findViewById(R.id.laserTimestamp)
        fun bind(timestamp: Timestamp){
            // Convert Firestore Timestamp to Date
            val date = timestamp.toDate()

            // Format the Date to a human-readable string
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val formattedTimestamp = sdf.format(date)

            // Set the formatted timestamp in the TextView
            timestampTextView.text = formattedTimestamp
        }
    }
}
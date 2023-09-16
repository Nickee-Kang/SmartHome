package com.example.smarthome

import android.content.ContentValues
import android.content.Intent
import android.media.session.MediaSessionManager.OnMediaKeyEventSessionChangedListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class LaserTripwire : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var laserTripwireAdapter: LaserTripwireAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laser_tripwire)

        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.laser_recycler)
        recyclerView.setHasFixedSize(true)

        val timestampList = mutableListOf<Timestamp>()
        laserTripwireAdapter = LaserTripwireAdapter(timestampList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = laserTripwireAdapter

        val backButton = findViewById<TextView>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }

        retrieveTimestamps()
    }

    private fun retrieveTimestamps() {
        val laserIntrusionCollection = db.collection("LaserIntrusion")

        laserIntrusionCollection
            .addSnapshotListener { value, error ->
                if (error != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val timestampList = mutableListOf<Timestamp>()

                for (document in value?.documents ?: emptyList()) {
                    if (document.contains("timestamp")) {
                        val timestamp = document.getTimestamp("timestamp")
                        if (timestamp != null) {
                            timestampList.add(timestamp)
                        }
                    }
                }

                // Sort the timestampList from latest to oldest
                timestampList.sortByDescending { it.seconds }

                // Update the adapter with the retrieved timestamps
                laserTripwireAdapter.updateData(timestampList)
            }
    }
}
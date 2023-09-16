package com.example.smarthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LaserTripwire : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var laserTripwireAdapter: LaserTripwireAdapter
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laser_tripwire)

        // Initialize Firebase Realtime Database
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()

        recyclerView = findViewById(R.id.laser_recycler)
        recyclerView.setHasFixedSize(true)

        val timestampList = mutableListOf<String>()
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
        val databaseReference = database.reference.child("laser_intrusions")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val timestampList = mutableListOf<String>()

                for (snapshot in dataSnapshot.children) {
                    val timestampString = snapshot.child("timestamp").getValue(String::class.java)
                    timestampString?.let {
                        timestampList.add(it)
                    }
                }

                // Log the retrieved timestamp data
                Log.d("Firebase Data", timestampList.toString())

                // Sort the timestampList from latest to oldest
                timestampList.sortByDescending { it }

                // Update the adapter with the retrieved timestamps
                laserTripwireAdapter.updateData(timestampList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event (if needed)
                Log.e("Database Error", error.message)
            }
        })
    }
}
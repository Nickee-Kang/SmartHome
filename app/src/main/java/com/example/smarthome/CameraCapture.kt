package com.example.smarthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.google.firebase.storage.FirebaseStorage


class CameraCapture : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cameraCaptureAdapter: CameraCaptureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_capture)

        recyclerView = findViewById(R.id.capture_recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create an instance of Firebase Storage
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        // List all items in the storage reference
        storageRef.listAll().addOnSuccessListener { result ->
            val imageReferences = result.items

            // Sort the image references by name (timestamps)
            imageReferences.sortByDescending { it.name }

            // Initialize and set the adapter for the RecyclerView
            cameraCaptureAdapter = CameraCaptureAdapter(imageReferences)
            recyclerView.adapter = cameraCaptureAdapter
        }

        val backButton = findViewById<TextView>(com.example.smarthome.R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }

    }
}


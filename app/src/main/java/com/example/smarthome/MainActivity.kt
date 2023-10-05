package com.example.smarthome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("laser")
        FirebaseMessaging.getInstance().subscribeToTopic("pressure")

        val laserCard = findViewById<CardView>(R.id.laser_card)
        laserCard.setOnClickListener{
            val intent = Intent(this, LaserTripwire::class.java)
            startActivity(intent)
        }

        val pressureCard = findViewById<CardView>(R.id.pressure_card)
        pressureCard.setOnClickListener{
            val intent = Intent(this, MatPressure::class.java)
            startActivity(intent)
        }

        val cameraCard = findViewById<CardView>(R.id.camera_card)
        cameraCard.setOnClickListener{
            val intent = Intent(this, CameraCapture::class.java)
            startActivity(intent)
        }
    }
}
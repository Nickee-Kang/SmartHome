package com.example.smarthome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val smartLockCard = findViewById<CardView>(R.id.smart_lock_card)
        smartLockCard.setOnClickListener{
            /*val intent = Intent(this, SmartLock::class.java)
            startActivity(intent)*/
        }

        val laserCard = findViewById<CardView>(R.id.laser_card)
        laserCard.setOnClickListener{
            val intent = Intent(this, LaserTripwire::class.java)
            startActivity(intent)
        }

        val pressureCard = findViewById<CardView>(R.id.pressure_card)
        pressureCard.setOnClickListener{
            /*val intent = Intent(this, MatPressure::class.java)
            startActivity(intent)*/
        }

        val cameraCard = findViewById<CardView>(R.id.camera_card)
        cameraCard.setOnClickListener{
            /*val intent = Intent(this, SmartCamera::class.java)
            startActivity(intent)*/
        }
    }
}
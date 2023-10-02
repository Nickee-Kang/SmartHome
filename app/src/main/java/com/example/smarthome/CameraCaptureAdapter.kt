package com.example.smarthome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class CameraCaptureAdapter(private val imageReferences: List<StorageReference>) :
    RecyclerView.Adapter<CameraCaptureAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val fileNameTextView: TextView = view.findViewById(R.id.fileName) // Add this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.camera_capture_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageReference = imageReferences[position]

        imageReference.downloadUrl.addOnSuccessListener { uri ->
            Picasso.get().load(uri).into(holder.imageView)

            // Set a placeholder image while loading
            Picasso.get()
                .load(uri.toString()) // Convert URI to a string
                .placeholder(R.drawable.placeholder_image) // Replace with your placeholder image resource
                .into(holder.imageView)
        }

        // Set the file name in the TextView
        holder.fileNameTextView.text = imageReference.name
    }

    override fun getItemCount(): Int {
        return imageReferences.size
    }
}
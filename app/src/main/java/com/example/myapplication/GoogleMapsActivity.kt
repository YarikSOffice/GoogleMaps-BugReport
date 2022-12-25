package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityGoogleMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val updateLabel: () -> Unit = {
            val cameraPosition = googleMap.cameraPosition
            binding.label1.text = "setOnCameraMoveListener. Zoom: ${cameraPosition.zoom}"
        }
        val updateLabelLegacy: () -> Unit = {
            val cameraPosition = googleMap.cameraPosition
            binding.label2.text = "setOnCameraChangeListener. Zoom: ${cameraPosition.zoom}"
        }
        updateLabel()
        updateLabelLegacy()

        googleMap.setOnCameraMoveListener { updateLabel() }
        googleMap.setOnCameraChangeListener { updateLabelLegacy() }
    }
}

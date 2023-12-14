package com.example.setbro

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.setbro.databinding.ActivityMainBinding
import com.ekn.gruzer.gaugelibrary.*
import android.widget.ImageButton
import androidx.fragment.app.*
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
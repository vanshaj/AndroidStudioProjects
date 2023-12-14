package com.example.servicesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.servicesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val intent = Intent(this, BackgroundService::class.java)
        intent.putExtra("name", "vanshaj")
        binding.serviceButton.setOnClickListener{
            startService(intent)
        }
        binding.stopService.setOnClickListener{
            stopService(intent)
        }
    }
}
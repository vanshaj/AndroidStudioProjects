package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMain3Binding
import com.example.myapplication.model.UserDetails

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userDetails = intent.extras?.getSerializable("userDetails", UserDetails::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)

        binding.apply {
            userDetailsText.text = "Hello ${userDetails?.name}, your age is ${userDetails?.age} and your address is ${userDetails?.address}"
        }
    }
}
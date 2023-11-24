package com.example.viewmodeldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.viewmodeldemo.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding = DataBindingUtil.setContentView(this@MainActivity2, R.layout.activity_main2 )
        binding.apply {
            backButton.setOnClickListener{
                val intent = Intent(this@MainActivity2, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
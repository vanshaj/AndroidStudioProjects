package com.example.viewmodeldemo

import android.content.Context
import android.content.Intent
 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    // var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.count.observe(this, Observer{
            binding.textCounter.text = it.toString()
        })

        binding.apply {
            textCounter.text = viewModel.count.toString()

            incrementButton.setOnClickListener{
                viewModel.updateCount()
                // textCounter.text = viewModel.count.toString()
            }

            nextButton.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)
                }

            })
        }
    }
}
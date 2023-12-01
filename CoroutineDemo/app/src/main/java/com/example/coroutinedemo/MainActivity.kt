package com.example.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinedemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.countButton.setOnClickListener{
            count++
            binding.countTextView.text = count.toString()
        }
        binding.downloadButton.setOnClickListener{
            // Launch downloading task in the background thread
            // CoroutineScope defines the scope of this coroutine which is limited to this {} area only
            // Also we have set the context of this coroutine to be Dispatcher.IO i.e it will be running in the background
            // Dispatchers.IO thread is used to comm with N/W tasks API calls, Local Database, Our Local File System
            // Dispatchers.Default thread is used for CPU intensive tasks e.g sorting a large list
//                CoroutineScope(Dispatchers.IO).launch {
//                    downloadUserData()
//                }
            mainActivityViewModel.getUserData()
            mainActivityViewModel.users.observe(this, Observer {
                myUsers -> myUsers?.forEach {
                    Log.i("Custom Tag", "name is ${it.name}")
            }
            })
        }
    }
    private suspend fun downloadUserData(){
        for (i in 1..2000) {
            // main thread is the UI thread job
            Log.i("Custom Tag", "Downloading user $i in ${Thread.currentThread().name}")
            binding.apply {
                // Switch the coroutine from Dispatchers.IO thread to Dispatchers.Main thread
                // If we don't use withContext then this code will not work, as Dispatchers.IO thread will not have access to the downloadTextView
                /* withContext is also a suspending function  i.e if some background task is going to be performed inside the suspend function then our thread
                   will not be blocked and the suspend function will again continue from the same place where it was  being left off */
                // Suspending function can only be called from a coroutine block or another suspending function only
                withContext(Dispatchers.Main) {
                    downloadTextView.text = "Downloading user $i"
                }
            }
            delay(100)
        }
    }
}
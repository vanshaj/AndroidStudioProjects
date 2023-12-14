package com.example.servicesdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BackgroundService : Service() {
    companion object {
        const val TAG = "mytag"
    }
    init{
        Log.i(TAG,"Service has been created")
    }
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i(TAG, "starting service")
        }
        val name = intent?.getStringExtra("name")
        Log.i(TAG, "name is $name")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(TAG, "destroying service")
        super.onDestroy()
    }
}
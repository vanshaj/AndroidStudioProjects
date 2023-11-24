package com.example.audiovideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import com.example.audiovideo.databinding.ActivityMain2Binding
import java.net.URI

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        var mediaController: MediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        var path: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.repolevel)
        binding.videoView.setVideoURI(path)
        binding.videoView.requestFocus()
        binding.videoView.start()
    }
}
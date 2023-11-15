package com.example.audiovideo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.example.audiovideo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())

        binding.apply {
            playButton.setOnClickListener{
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.sound)
                    initializeSeekBar()
                }
                mediaPlayer?.start()
            }
            pauseButton.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    mediaPlayer?.pause()
                }
            })
            stopButton.setOnClickListener{
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
                handler.removeCallbacks(runnable)
                seekBar.progress = 0
                seekBarTextView.text = "0:0"
            }
        }
    }

    fun initializeSeekBar() {
        binding.apply {

            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if(fromUser) {
                        mediaPlayer?.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            })

            seekBar.max = mediaPlayer!!.duration
            runnable = Runnable {
                seekBar.progress = mediaPlayer!!.currentPosition
                val minutes = (mediaPlayer!!.currentPosition / 1000) / 60
                val seconds = (mediaPlayer!!.currentPosition / 1000) % 60
                seekBarTextView.text = "$minutes:$seconds"
                handler.postDelayed(runnable, 1000)
            }
            handler.postDelayed(runnable, 1000)
        }
    }
}

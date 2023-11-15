package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val submit_button = findViewById<Button>(R.id.submit_button)
//        val enter_text = findViewById<EditText>(R.id.enter_text)
//        val display_text = findViewById<TextView>(R.id.display_text)
//        submit_button.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                val enter_text_string = enter_text.text.toString()
//                display_text.text = "Hello from ${enter_text_string}"
//            }
//        })
        // By using object inside the argument
//        binding.enterText.addTextChangedListener(object: TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s?.length ?: 0 != 0){
//                    binding.nextButton.visibility = VISIBLE
//                } else {
//                    binding.nextButton.visibility = INVISIBLE
//                }
//            }
//        })
    }

    /*
     When we press back button only onRestart, onStart, onResume will get called
     When we startActivity all onCreate, onStart, onResume gets called
     */
    override fun onResume() {
        super.onResume()
        Toast.makeText(this@MainActivity, "I am back baby on resume", Toast.LENGTH_SHORT ).show()
        binding.enterText.addTextChangedListener {// seems like it works afterTextChanged
            // Log.println(Log.DEBUG,"MSG-", "Text is being changed")
            //if(binding.enterText.text.toString() != "") {
            if(it?.length ?: 0 != 0){
                binding.nextButton.visibility = VISIBLE
            } else {
                binding.nextButton.visibility = INVISIBLE
            }
        }
        binding.submitButton.setOnClickListener {
            var enter_text_string: String
            enter_text_string = binding.enterText.text.toString()
            displayMessage(enter_text_string)
//            val enter_text_string = enter_text.text.toString()
//            display_text.text = "Hello ${enter_text_string}"
        }
        binding.nextButton.setOnClickListener{
            gotoNext()
        }
    }

    fun displayMessage(message: String) {
        binding.apply {// if you use apply then no need to use object name to call the fields or functions rather than binding.displayText we can directly use displayText
            val lenMessage = message.length
            if(lenMessage == 0) {
                displayText.text = ""
                Toast
                    .makeText(this@MainActivity, "Enter name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val display_message = "Bye, dear ${message}, you are not allowed here"
                displayText.text =  display_message // Set the text for the text view
                enterText.text.clear() // Clear the text inside the edit text
            }
        }
    }

    fun gotoNext(){
        binding.apply {
            val name = enterText.text.toString()
            if(name == "") {
                Toast
                    .makeText(this@MainActivity, "Enter name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val bundle = Bundle()
                bundle.putString("name", name) // Bundle is a key value pair
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra("name", name) // intent stores bundle in a key value pair
                displayText.text = ""
                startActivity(intent)
            }
        }
    }
}

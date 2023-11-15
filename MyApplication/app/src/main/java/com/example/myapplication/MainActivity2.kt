package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.UserDetails
import com.example.myapplication.model.checkIfNullOrEmpty
import com.google.gson.Gson


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var userDetails: UserDetails? = null
    private lateinit var sp: SharedPreferences
    private lateinit var sp_edit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        val name = bundle?.getString("name") ?: ""

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        sp = getSharedPreferences("sf_user", MODE_PRIVATE)
        sp_edit = sp.edit()

        binding.apply {
            displayMessage.text = "Welcome ${name}"

            backButton.setOnClickListener{
                val intent: Intent = Intent(this@MainActivity2, MainActivity::class.java)
                startActivity(intent)
            }

            nextActivity2Button.setOnClickListener{
                if (ageText.text.isEmpty() || emailText.text.isEmpty() || addressText.text.isEmpty()) {
                    if (ageText.text.isEmpty()) {
                        ageText.error = "Enter age"
                    }
                    if (emailText.text.isEmpty()) {
                        emailText.error = "Enter email"
                    }
                    if (addressText.text.isEmpty()) {
                        addressText.error = "Enter address"
                    }
                } else {
                    userDetails = UserDetails(
                        name = name,
                        age = ageText.text.toString(),
                        email = emailText.text.toString(),
                        address = addressText.text.toString()
                    )
                    val intent: Intent = Intent(this@MainActivity2, MainActivity3::class.java)
                    intent.putExtra("userDetails", userDetails)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val gson = Gson()
        if(!userDetails.checkIfNullOrEmpty()){
            val jsonString = gson.toJson(userDetails)
            Log.println(Log.DEBUG, "app-", "set json of user details $jsonString")
            sp_edit.apply {
                putString("user_details", jsonString)
                commit()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val gson = Gson()
        val jsonString = sp.getString("user_details",null)
        if (!jsonString.isNullOrEmpty()) {
            Log.println(Log.DEBUG,"app-", "get json of user details $jsonString")
            val presentUserDetails: UserDetails = gson.fromJson(jsonString, UserDetails::class.java)
            binding.apply {
                ageText.setText(Integer.toString(presentUserDetails.age))
                emailText.setText(presentUserDetails.email)
                addressText.setText(presentUserDetails.address)
            }
        }
    }
}
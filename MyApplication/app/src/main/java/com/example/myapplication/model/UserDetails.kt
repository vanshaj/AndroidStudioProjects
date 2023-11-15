package com.example.myapplication.model

import android.text.InputType
import java.io.Serializable

class UserDetails (name: String, age: String, email:String, address: String) : Serializable{
    lateinit var name: String
    var age: Int = 0
    lateinit var email: String
    lateinit var address: String

    init {
        this.name = name
        this.age = Integer.valueOf(age)
        this.email = email
        this.address = address
    }

}
public fun UserDetails?.checkIfNullOrEmpty(): Boolean {
    val isNull = this?.let {
        this.name.isNullOrEmpty() && this.age == 0 && this.email.isNullOrEmpty() && this.address.isNullOrEmpty()
    } ?: true
    return isNull
}

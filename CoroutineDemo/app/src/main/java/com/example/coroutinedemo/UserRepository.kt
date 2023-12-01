package com.example.coroutinedemo

import kotlinx.coroutines.delay

class UserRepository  {
    suspend fun getUsers() : List<User> {
        delay(8000)
        val users: List<User> = listOf(
            User(1, "Vanshaj"),
            User(2, "Himanshu"),
            User(3, "Abhishk")
        )
        return users
    }
}
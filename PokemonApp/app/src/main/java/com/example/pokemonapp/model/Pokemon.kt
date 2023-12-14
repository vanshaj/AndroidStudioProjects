package com.example.pokemonapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String)
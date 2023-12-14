package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class AllPokemonResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var results: ArrayList<Pokemon>)
package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class AllPokemonResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Pokemon>)
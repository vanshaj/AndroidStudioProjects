package com.example.pokemonapp.model

data class AllPokemonResponse(val count: Int, val next: String, val previous: String, val results: List<Pokemon>)
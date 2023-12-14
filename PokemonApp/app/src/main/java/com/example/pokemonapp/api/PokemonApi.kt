package com.example.pokemonapp.api

import com.example.pokemonapp.model.AllPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int = 20, @Query("limit") limit: Int = 20): Response<AllPokemonResponse?>
}
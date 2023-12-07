package com.example.pokemonapp.model

import com.example.pokemonapp.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PokemonRepository {

    suspend fun getAll(): AllPokemonResponse? {
        var pokemons: AllPokemonResponse?  = null
        val result = RetrofitInstance.api.getAllPokemon()
        if (result.code() == 200) {
            pokemons = result.body()
        }
        return pokemons
    }

    fun getPokemon(id: Int): Pokemon {
        return Pokemon("pikachu", "https://abc")
    }
}

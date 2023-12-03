package com.example.pokemonapp.model

class PokemonRepository {

    fun getAll(): List<Pokemon> {
        val pokemons: List<Pokemon>  = listOf(
            Pokemon("bulbasaur", "https://abc"),
            Pokemon("pikachu", "https://abc")
        )
        return pokemons
    }

    fun getPokemon(id: Int): Pokemon {
        return Pokemon("pikachu", "https://abc")
    }
}
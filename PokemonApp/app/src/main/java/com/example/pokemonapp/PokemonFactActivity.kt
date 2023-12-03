package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.pokemonapp.databinding.ActivityPokemonFactBinding
import com.example.pokemonapp.model.Pokemon
import java.io.Serializable

class PokemonFactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonFactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_fact)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_fact )
        val pokemon: Pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        binding.pokemonNameText.text = pokemon.name
    }
}
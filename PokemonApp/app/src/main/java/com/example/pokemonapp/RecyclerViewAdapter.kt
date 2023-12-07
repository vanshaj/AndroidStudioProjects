package com.example.pokemonapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.model.AllPokemonResponse
import com.example.pokemonapp.model.Pokemon

class RecyclerViewAdapter(var items: AllPokemonResponse?) : RecyclerView.Adapter<RecyclerViewAdapter.CustomHolder>(){

    var onItemClick: ((Pokemon) -> Unit)? = null
    class CustomHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView
        val url: TextView

        init {
            name = view.findViewById(R.id.pokemonName)
            url = view.findViewById(R.id.pokemonUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_pokemon, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.results?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val pokemon = items?.results?.get(position)
        holder.name.text = pokemon?.name
        holder.url.text = pokemon?.url
        holder.itemView.setOnClickListener{
            if (pokemon != null) {
                onItemClick?.invoke(pokemon)
            }
        }
    }
}
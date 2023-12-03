package com.example.pokemonapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.model.Pokemon

class RecyclerViewAdapter(private val items: List<Pokemon>) : RecyclerView.Adapter<RecyclerViewAdapter.CustomHolder>(){

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
        return items.size
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val pokemon = items[position]
        holder.name.text = pokemon.name
        holder.url.text = pokemon.url
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(pokemon)
        }
    }
}
package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val items: List<String>) : RecyclerView.Adapter<RecyclerAdapter.CustomHolder>() {

    class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
        val data: TextView

        init {
            data = view.findViewById(R.id.listItemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.data.text = items[position]
    }
}
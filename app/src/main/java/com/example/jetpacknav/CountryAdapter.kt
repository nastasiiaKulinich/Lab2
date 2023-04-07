package com.example.jetpacknav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryAdapter(
    val fragment: FirstFragment,
    var countries: ArrayList<Country>,
    val listner: Listener
) :RecyclerView.Adapter<CountryAdapter.AnimalViewHolder>()  {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewCountry : TextView = itemView.findViewById(R.id.nameOfCountry)
        var textViewCapital : TextView = itemView.findViewById(R.id.nameOfCapital)
        var image : ImageView = itemView.findViewById(R.id.imageOfCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listner.onClick(position)
        }
        Glide.with(fragment)
            .load(countries.get(position).photo).fitCenter().into(holder.image)
        holder.textViewCountry.text = countries.get(position).name
        holder.textViewCapital.text = countries.get(position).capital
    }

    interface Listener {
        fun onClick(itemView: Int)
    }
}
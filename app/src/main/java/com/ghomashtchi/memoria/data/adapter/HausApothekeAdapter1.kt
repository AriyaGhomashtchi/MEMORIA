package com.ghomashtchi.memoria.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.ui.HausapothekefragmentDirections

class HausApothekeAdapter1: RecyclerView.Adapter<HausApothekeAdapter1.ItemViewHolder>() {

    private var dataset = listOf<com.ghomashtchi.memoria.testApi.Result>()

    class ItemViewHolder(view: View): ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.hausItem_imageview)
        val cardview: CardView = view.findViewById(R.id.hausItem_cardview)
        val name: TextView = view.findViewById(R.id.hausItem_name_textview)
    }

    fun submitlist(list: List<com.ghomashtchi.memoria.testApi.Result>){
        dataset = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.haus_category_item, parent, false)
        return ItemViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val medicine = dataset[position]
        holder.image.load(medicine.image)
        holder.name.text = medicine.name
        holder.cardview.setOnClickListener {
            Navigation.findNavController(holder.itemView)
                .navigate(HausapothekefragmentDirections.actionHausapothekefragmentToProduktBesschreibungFragment(medicine.id))
        }
    }
}
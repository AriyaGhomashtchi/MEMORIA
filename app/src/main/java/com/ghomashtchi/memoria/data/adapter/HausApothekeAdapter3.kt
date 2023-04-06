package com.ghomashtchi.memoria.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.model.Medicine_dto

class HausApothekeAdapter3: RecyclerView.Adapter<HausApothekeAdapter3.ItemViewHolder>() {

    private var dataset = mutableListOf<Medicine_dto>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.categoryItem_imageview)
        val cardview: CardView = view.findViewById(R.id.hausItem_cardview)
        val name: TextView = view.findViewById(R.id.categoryItem_name_textview)
    }

    fun submitlist(list: MutableList<Medicine_dto>){
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

    }
}
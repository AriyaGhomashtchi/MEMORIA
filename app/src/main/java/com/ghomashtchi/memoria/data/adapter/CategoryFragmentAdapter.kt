package com.ghomashtchi.memoria.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.model.Medicine_dto
import com.ghomashtchi.memoria.ui.CategoryFragmentDirections

class CategoryFragmentAdapter : RecyclerView.Adapter<CategoryFragmentAdapter.ItemViewHolder>() {

    private var dataset = listOf<com.ghomashtchi.memoria.data.medicineApi.Result>()

    class ItemViewHolder(view: View) : ViewHolder(view) {
        val cardview: CardView = view.findViewById(R.id.categoryItem_cardview)
        val image: ImageView = view.findViewById(R.id.categoryItem_imageview)
        val name: TextView = view.findViewById(R.id.categoryItem_name_textview)
    }

    fun submitList(list: List<com.ghomashtchi.memoria.data.medicineApi.Result>) {
        dataset = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.med_item, parent, false)
        return ItemViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val medicine = dataset[position]
        holder.image.load(medicine.image)
        holder.name.text = medicine.name
        holder.cardview.setOnClickListener {
            Navigation.findNavController(holder.itemView).navigate(
                CategoryFragmentDirections.actionCategoryFragmentToProduktBesschreibungFragment(
                    medicine.id
                )
            )
        }

    }
}
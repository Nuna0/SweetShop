package com.example.sweetshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetshop.R
import com.example.sweetshop.model.Categories

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    private  var item = emptyList<Categories>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_category_recycler,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<Categories>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameCategory: TextView = itemView.findViewById(R.id.nameCategory)

        fun bind(model: Categories){
            nameCategory.text = model.name
        }
    }
}
package com.example.sweetshop.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetshop.R
import com.example.sweetshop.model.Catalog

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {
    private var item = arrayListOf<Catalog>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_header,
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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(model: ArrayList<Catalog>) {
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameCategory: TextView = itemView.findViewById(R.id.nameCategory)

        fun bind(model: Catalog) {

            nameCategory.text = model.nameCategory
            // productAdapter.setData(model.products as ArrayList)

        }
    }
}

package com.example.sweetshop.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetshop.R
import com.example.sweetshop.model.Catalog
import com.example.sweetshop.model.Categories

class CatalogAdapter(
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>(){
    private  var item = arrayListOf<Catalog>()
    private  val productAdapter by lazy(LazyThreadSafetyMode.NONE) { ProductAdapter() }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_catalog_recycler,
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

    fun setData(model: ArrayList<Catalog>){
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nameCategory: TextView = itemView.findViewById(R.id.nameCategory)
        val recycler: RecyclerView = itemView.findViewById(R.id.productRecycler)

        fun bind(model: Catalog){

            val adapter = ProductAdapter()
            nameCategory.text = model.nameCategory
            adapter.setData(model.products)
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(itemView.context,2)

        }
    }
}
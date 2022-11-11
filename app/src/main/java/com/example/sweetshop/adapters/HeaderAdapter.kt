package com.example.sweetshop.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.model.Catalog
import com.example.sweetshop.model.Header
import com.example.sweetshop.screens.CatalogFragmentDirections
import kotlinx.android.synthetic.main.item_header_recycler.view.*

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {
    private var item = arrayListOf<Header>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_header_recycler,
            parent,
            false
        )
        return ViewHolder(itemView)
    }
    //Водка

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)

    }
    //Общее положение как?

    override fun getItemCount(): Int {
        return item.size
    }
    //арип, я люблю тебя

    @SuppressLint("NotifyDataSetChanged")
    fun setData(model: ArrayList<Header>) {
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.image_ofFirstRec)

        fun bind(model: Header) {

            Glide.with(itemView.context).load(model.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(image)            // productAdapter.setData(model.products as ArrayList)


            itemView.constraint.setOnClickListener {
                val action = CatalogFragmentDirections.actionCatalogFragmentToHeaderOpenFragment(model)
                itemView.findNavController().navigate(action)
            }
        }

    }
}

package com.example.sweetshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.model.Products
import com.example.sweetshop.screens.CatalogFragmentDirections

class ProductAdapter(
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    private  var item = arrayListOf<Products>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product_recycler,
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

    fun setData(model: ArrayList<Products>){
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameProduct: TextView = itemView.findViewById(R.id.nameProduct)
        val massProduct: TextView = itemView.findViewById(R.id.massProduct)
        val image: ImageView = itemView.findViewById(R.id.image)
        val addBtn: TextView = itemView.findViewById(R.id.addProduct)
        val plusBtn: TextView = itemView.findViewById(R.id.plusProduct)
        val minusBtn: TextView = itemView.findViewById(R.id.minusProduct)
        val countProduct: TextView = itemView.findViewById(R.id.countProduct)

        var count = 1

        fun bind(model: Products){
            nameProduct.text = model.name
            massProduct.text = model.mass
           Glide.with(itemView.context)
               .load(model.image)
               .centerCrop()
               .apply {
                   RequestOptions().apply {
                       transform(CenterCrop(), GranularRoundedCorners(24f, 24f, 0f, 0f))
                   }
               }
               .into(image)

            addBtn.setOnClickListener {
                addBtn.visibility = View.GONE
                plusBtn.visibility = View.VISIBLE
                minusBtn.visibility = View.VISIBLE
                countProduct.visibility = View.VISIBLE
                countProduct.setText("1")
            }

            plusBtn.setOnClickListener {
                count = countProduct.getText().toString().toInt() + 1
                countProduct.setText(count.toString())
                minusBtn.isEnabled = true
                plusBtn.isEnabled = true
                if(count<=1){
                    addBtn.visibility = View.VISIBLE
                    plusBtn.visibility = View.GONE
                    minusBtn.visibility = View.GONE
                    countProduct.visibility = View.GONE
                }
            }

            minusBtn.setOnClickListener {
                if(count<=1){
                    addBtn.visibility = View.VISIBLE
                    plusBtn.visibility = View.GONE
                    minusBtn.visibility = View.GONE

                    countProduct.visibility = View.GONE
                }else {
                    count = countProduct.getText().toString().toInt() - 1
                    countProduct.setText(count.toString())
                    minusBtn.isEnabled = true
                    plusBtn.isEnabled = true
                }

            }

            itemView.setOnClickListener {
                val action = CatalogFragmentDirections.actionCatalogFragmentToProductCard(model)
                itemView.findNavController().navigate(action)
            }

        }
    }
}
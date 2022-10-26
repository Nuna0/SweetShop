package com.example.sweetshop.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetshop.database.ProductsInBasket
import com.example.sweetshop.database.RoomViewModel
import com.example.sweetshop.databinding.ItemBasketProductRecyclerBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class BasketProductAdapter(
    val activity: Fragment
): RecyclerView.Adapter<BasketProductAdapter.ViewHolder>() {
    private var item = arrayListOf<ProductsInBasket>()
    lateinit var sharedPreferences: SharedPreferences
    var count=1
    private lateinit var viewModel: RoomViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemBasketProductRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        sharedPreferences = parent.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(model:ArrayList<ProductsInBasket>){
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val itemBinding: ItemBasketProductRecyclerBinding): RecyclerView.ViewHolder(itemBinding.root) {

        private fun saveCount(id: Int, number: Int) {
            sharedPreferences = itemView.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt(id.toString(), number).apply()
        }

        private fun loadCount(id: Int): Int {
            sharedPreferences = itemView.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            return sharedPreferences.getInt(id.toString(), 0)
        }

        private fun deleteCount(id: Int) {
            sharedPreferences = itemView.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(id.toString()).apply()
        }

        fun bind(model: ProductsInBasket){

            count = itemBinding.countProduct.text.toString().toInt()
           /* if(count!=itemBinding.countProduct.text.toString().toInt()){
                count = loadCount(model.idProduct)
            }*/

            with(itemBinding){
                title.text = model.name
                mass.text = model.mass
                price.text = model.price
                countProduct.text = model.presence
                Glide.with(itemView.context).load(model.image).into(image)

                minusProduct.setOnClickListener {
                    count = Integer.parseInt(countProduct.text.toString())-1
                    countProduct.text = count.toString()
                    if(count<=0){
                        val product = ProductsInBasket(
                            model.idProduct,
                            model.name,
                            model.image,
                            model.price,
                            count.toString(),
                            model.mass,
                            model.category
                        )
                        viewModel = ViewModelProvider(activity)[RoomViewModel::class.java]
                        viewModel.deleteTask(product)
                    }
                }

                plusProduct.setOnClickListener {
                    count = Integer.parseInt(countProduct.text.toString())+1
                    countProduct.text = count.toString()
                    saveCount(model.idProduct,count)
                }
            }


        }
    }
}
package com.example.sweetshop.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.database.ProductsInBasket
import com.example.sweetshop.database.RoomViewModel
import com.example.sweetshop.model.Products
import com.example.sweetshop.screens.CatalogFragmentDirections
import com.example.sweetshop.viewmodel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
 class ProductAdapter(
    val activity: Fragment,
   // var addProduct:(ProductsInBasket)->Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    private  var item = arrayListOf<Products>()
    lateinit var sharedPreferences: SharedPreferences


    private lateinit var viewModel: RoomViewModel

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product_recycler,
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

    fun setData(model: ArrayList<Products>){
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameProduct: TextView = itemView.findViewById(R.id.nameProduct)
        val massProduct: TextView = itemView.findViewById(R.id.massProduct)
        val image: ImageView = itemView.findViewById(R.id.image)
        val addBtn: TextView = itemView.findViewById(R.id.addProduct)
        val plusBtn: TextView = itemView.findViewById(R.id.plusProduct)
        val minusBtn: TextView = itemView.findViewById(R.id.minusProduct)
        val countProduct: TextView = itemView.findViewById(R.id.countProduct)
        val price: TextView = itemView.findViewById(R.id.price)
        val cardView: CardView = itemView.findViewById(R.id.cardView)


        var count = 1

        private fun saveCount(id: Int, number: Int) {
            sharedPreferences = itemView.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt(id.toString(), number).apply()
        }

        private fun loadCount(id: Int): Int {
            sharedPreferences = image.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            return sharedPreferences.getInt(id.toString(), 0)
        }

        private fun deleteCount(id: Int) {
            sharedPreferences = itemView.context.getSharedPreferences("Count", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(id.toString()).apply()
        }

        fun addProductToBasket(model: Products){
            val id = model.idProduct.toInt()

            Glide.with(itemView.context)
                .load(model.image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(image)
            //Toast.makeText(itemView.context, "Данные сохранены", Toast.LENGTH_SHORT).show()
            val product = ProductsInBasket(
                id,
                model.name,
                model.image,
                model.price,
                count.toString(),
                model.mass,
                model.category
            )
            viewModel = ViewModelProvider(activity)[RoomViewModel::class.java]
            viewModel.addTask(product)

        }
         fun deleteProductFromBasket(model: Products){
             val id = model.idProduct.toInt()
             val product = ProductsInBasket(
                 id,
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

        fun bind(model: Products){

            nameProduct.text = model.name
            massProduct.text = itemView.context.getString(R.string.mass, model.mass)
            price.text = itemView.context.getString(R.string.price, model.price)
            count= loadCount(model.idProduct.toInt())
            countProduct.text = count.toString()

            Glide.with(itemView.context)
                .load(model.image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(image)

            val id = model.idProduct.toInt()

            if(count<=0){
                addBtn.visibility = View.VISIBLE
                plusBtn.visibility = View.GONE
                minusBtn.visibility = View.GONE
                countProduct.visibility = View.GONE

            }else{
                addBtn.visibility = View.GONE
                plusBtn.visibility = View.VISIBLE
                minusBtn.visibility = View.VISIBLE
                countProduct.visibility = View.VISIBLE
            }


            addBtn.setOnClickListener {

                count = Integer.parseInt(countProduct.text.toString())+1
                countProduct.text = count.toString()
                saveCount(model.idProduct.toInt(), count)

                minusBtn.isEnabled = true
                plusBtn.isEnabled = true
                if(count<=0){
                    addBtn.visibility = View.VISIBLE
                    plusBtn.visibility = View.GONE
                    minusBtn.visibility = View.GONE
                    countProduct.visibility = View.GONE
                    deleteProductFromBasket(model)
                }else{
                    addBtn.visibility = View.GONE
                    plusBtn.visibility = View.VISIBLE
                    minusBtn.visibility = View.VISIBLE
                    countProduct.visibility = View.VISIBLE
                    addProductToBasket(model)
                }

            }

            plusBtn.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                cardView.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                count = Integer.parseInt(countProduct.text.toString())+1
                countProduct.text = count.toString()
                saveCount(model.idProduct.toInt(), count)

                minusBtn.isEnabled = true
                plusBtn.isEnabled = true
                if(count<=0){
                    addBtn.visibility = View.VISIBLE
                    plusBtn.visibility = View.GONE
                    minusBtn.visibility = View.GONE
                    countProduct.visibility = View.GONE
                    deleteProductFromBasket(model)

                }else{
                    addBtn.visibility = View.GONE
                    plusBtn.visibility = View.VISIBLE
                    minusBtn.visibility = View.VISIBLE
                    countProduct.visibility = View.VISIBLE
                    addProductToBasket(model)
                }

            }

            minusBtn.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING)
                count = Integer.parseInt(countProduct.text.toString())-1
                countProduct.text = count.toString()
                deleteCount(model.idProduct.toInt())
                minusBtn.isEnabled = true
                plusBtn.isEnabled = true
                if(count<=0){
                    addBtn.visibility = View.VISIBLE
                    plusBtn.visibility = View.GONE
                    minusBtn.visibility = View.GONE
                    countProduct.visibility = View.GONE
                    deleteProductFromBasket(model)

                }else {
                    addBtn.visibility = View.GONE
                    plusBtn.visibility = View.VISIBLE
                    minusBtn.visibility = View.VISIBLE
                    countProduct.visibility = View.VISIBLE
                    addProductToBasket(model)
                }

            }


            itemView.setOnClickListener {
                val action = CatalogFragmentDirections.actionCatalogFragmentToProductCard(model)
                itemView.findNavController().navigate(action)
            }

        }
    }
}
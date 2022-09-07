package com.example.sweetshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetshop.R
import com.example.sweetshop.databinding.ItemCategoryRecyclerBinding
import com.example.sweetshop.model.Categories

class CategoryAdapter(
    private var clickOnItem:(Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    private  var item = arrayListOf<Categories>()

    var selectedItemPos=0
    var lastItemSelectedPos = -1

    companion object {
        val NOT_SELECTED = -1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = ItemCategoryRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,
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

    fun setData(model: ArrayList<Categories>){
        item.run {
            clear()
            addAll(model)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBindind: ItemCategoryRecyclerBinding): RecyclerView.ViewHolder(itemBindind.root) {

        fun bind(model: Categories){
            with(itemBindind){

                nameCategory.text = model.name

                if (selectedItemPos == NOT_SELECTED) {
                    container.setBackgroundResource(R.color.white)
                } else {
                    if (selectedItemPos == adapterPosition) {
                        container.setBackgroundResource(R.drawable.selected_category)
                    } else {
                        container.setBackgroundResource(R.color.white)
                    }
                }

                itemView.setOnClickListener {

                    if (selectedItemPos == adapterPosition) {
                        notifyItemChanged(adapterPosition)
                    } else {
                        lastItemSelectedPos = selectedItemPos
                        selectedItemPos = adapterPosition

                        notifyItemChanged(lastItemSelectedPos, Unit)
                        notifyItemChanged(selectedItemPos, Unit)

                    }

                    clickOnItem(adapterPosition)


                }

            }


        }
    }
}
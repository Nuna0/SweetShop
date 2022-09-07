package com.example.sweetshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetshop.R
import com.example.sweetshop.model.Catalog
import com.example.sweetshop.model.Header
import com.example.sweetshop.repository.Repository
import com.example.sweetshop.viewmodel.MainViewModel
import com.example.sweetshop.viewmodel.MainViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CatalogAdapter(
    val activity: Fragment
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>(){
    private  var item = arrayListOf<Catalog>()
    private  var headerr = arrayListOf<Header>()

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

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
        val headerAdapter = HeaderAdapter()
     /*   if(position==0){
            viewModel = ViewModelProvider(activity,viewModelFactory).get(MainViewModel::class.java)
            viewModel.getMainModel()
            viewModel.mainResponse.observe(activity, Observer { response->
                headerAdapter.setData(response.header)
                holder.recycler.adapter=headerAdapter
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context,LinearLayoutManager.HORIZONTAL, false)
            })
        }
*/
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(model: ArrayList<Catalog>){
        item.run {
            clear()
            addAll(model)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nameCategory: TextView = itemView.findViewById(R.id.nameCategory)
        val recycler: RecyclerView = itemView.findViewById(R.id.productRecycler)

        fun bind(model: Catalog){
            val adapter = ProductAdapter(activity)
            nameCategory.text = model.nameCategory

            adapter.setData(model.products)
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(itemView.context,2)

        }
    }
}
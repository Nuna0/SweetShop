package com.example.sweetshop.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetshop.adapters.CatalogAdapter
import com.example.sweetshop.adapters.CategoryAdapter
import com.example.sweetshop.adapters.ProductAdapter
import com.example.sweetshop.databinding.FragmentCatalogBinding
import com.example.sweetshop.model.Products
import com.example.sweetshop.repository.Repository
import com.example.sweetshop.viewmodel.MainViewModel
import com.example.sweetshop.viewmodel.MainViewModelFactory

class CatalogFragment : Fragment() {

    private var _binding:FragmentCatalogBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    private  val categoryAdapter by lazy { CategoryAdapter() }
    private  val catalogAdapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        addCategoryRecycler()
        addCatalogRecycler()



    }

    fun addCategoryRecycler(){
        val recycler = binding.categoryRecycler
        viewModel.getMainModel()
        viewModel.mainResponse.observe(this, Observer { response->
            categoryAdapter.setData(response.categories)
            recycler.adapter = categoryAdapter
        })
    }

    fun addCatalogRecycler(){
        val recycler = binding.catalogRecycler
        viewModel.getMainModel()
        viewModel.mainResponse.observe(this, Observer { response->

            catalogAdapter.setData(response.catalog)

            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = catalogAdapter

        })
    }

}
package com.example.sweetshop.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetshop.R
import com.example.sweetshop.adapters.BasketProductAdapter
import com.example.sweetshop.adapters.CatalogAdapter
import com.example.sweetshop.database.RoomViewModel
import com.example.sweetshop.databinding.FragmentBasketBinding
import com.example.sweetshop.repository.Repository
import com.example.sweetshop.viewmodel.MainViewModel
import com.example.sweetshop.viewmodel.MainViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class BasketFragment : Fragment() {

    private  var _binding: FragmentBasketBinding?=null
    private  val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    private lateinit var mTaskViewModel: RoomViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlaceOrder.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_placeOrderFragment)
        }
        getProductList()
    }

    fun getProductList(){

        val basketAdapter = BasketProductAdapter(this)
        mTaskViewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        mTaskViewModel.getAllTasks.observe(viewLifecycleOwner, Observer { product ->
            basketAdapter.setData(product as ArrayList)
            binding.basketRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.basketRecycler.adapter = basketAdapter
            if(product.isEmpty()){
                binding.frame.visibility = View.VISIBLE
                binding.scroll.visibility = View.GONE
            }
        })

    }



}
package com.example.sweetshop.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sweetshop.R
import com.example.sweetshop.databinding.FragmentCatalogBinding
import com.example.sweetshop.databinding.FragmentPlaceOrderBinding


class PlaceOrderFragment : Fragment() {

    private var _binding: FragmentPlaceOrderBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaceOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
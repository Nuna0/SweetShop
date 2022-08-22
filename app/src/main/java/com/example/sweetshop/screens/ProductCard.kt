package com.example.sweetshop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sweetshop.databinding.FragmentProductCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.content.res.ColorStateList
import android.graphics.Color

import android.graphics.PorterDuff
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class ProductCard: BottomSheetDialogFragment() {

    private var _binding: FragmentProductCardBinding ?=null
    private val binding get()= _binding!!
    private val args by navArgs<ProductCardArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductCardBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheet = view.parent as View
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)


        Glide.with(requireContext()).load(args.currentProduct.image).apply(
            RequestOptions.bitmapTransform(
            RoundedCorners(40)
        )).into(binding.image)
        binding.description.text = args.currentProduct.description
        binding.nameMass.text = "${args.currentProduct.name} ${args.currentProduct.mass}"
        binding.price.text = args.currentProduct.price
        binding.close.setOnClickListener {
            dialog?.cancel()
        }
    }
}
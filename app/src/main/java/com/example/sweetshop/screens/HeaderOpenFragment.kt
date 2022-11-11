package com.example.sweetshop.screens

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sweetshop.CustPagerTransformer
import com.example.sweetshop.adapters.SlidingImageAdapter
import com.example.sweetshop.databinding.FragmentHeaderOpenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_header_open.*
import com.google.android.material.bottomsheet.BottomSheetBehavior

import android.widget.FrameLayout

import com.google.android.material.bottomsheet.BottomSheetDialog




class HeaderOpenFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentHeaderOpenBinding?=null
    private val binding get() = _binding!!
    private  val args by navArgs<HeaderOpenFragmentArgs>()


   /* override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog: DialogInterface ->
            val dialogc = dialog as BottomSheetDialog
            // When using AndroidX the resource can be found at com.google.android.material.R.id.design_bottom_sheet
            val bottomSheet =
                dialogc.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val bottomSheetBehavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.peekHeight = Resources.getSystem().getDisplayMetrics().heightPixels
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        return bottomSheetDialog
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeaderOpenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = activity?.let {
            SlidingImageAdapter(
                requireContext(),
                args.header.imgMax,
            )
        }
        pager.apply {
            this.adapter = pagerAdapter
            setPageTransformer(false,
                CustPagerTransformer(requireContext())
            )
            clipToPadding = false
        }

        indicator.setViewPager(pager)
        val density = resources.displayMetrics.density
        indicator.radius = density * 3

        binding.icCancel.setOnClickListener {
            findNavController().popBackStack()
        }

    }


}
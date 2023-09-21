package com.example.rentclothes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rentclothes.R
import com.example.rentclothes.databinding.CardFragemntBinding


class CardFragment : Fragment() {
    private lateinit var binding:CardFragemntBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardFragemntBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrayOfSize = arrayOf("Size S", "Size M", "Size L", "Size Free")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayOfSize
        )
        binding.SpinnerSize.adapter = arrayAdapter
        changeStatusBarColor(R.color.background_color)
    }
    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = requireActivity().window
        val color: Int = ContextCompat.getColor(requireContext(), colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
}
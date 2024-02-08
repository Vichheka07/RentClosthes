package com.example.rentclothes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rentclothes.R
import com.example.rentclothes.adapter.CardAdapter
import com.example.rentclothes.databinding.CardFragemntBinding
import com.example.rentclothes.model.CardItems


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

        changeStatusBarColor(R.color.background_color)
        val itemsList = ArrayList<CardItems>()
        CardScreen(itemsList);
    }

    private fun CardScreen(itemsCard: ArrayList<CardItems>) {
        val adapter = CardAdapter();
        binding.imageCard.adapter =adapter
        adapter.submitList(itemsCard)

    }

    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = requireActivity().window
        val color: Int = ContextCompat.getColor(requireContext(), colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
}
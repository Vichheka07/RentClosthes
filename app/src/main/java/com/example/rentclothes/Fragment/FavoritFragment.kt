package com.example.myapplication.Fragment

import android.R
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rentclothes.databinding.FavoritFragmentBinding


class FavoritFragment:Fragment() {
    private lateinit var binding: FavoritFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }
}
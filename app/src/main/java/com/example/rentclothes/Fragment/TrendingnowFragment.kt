package com.example.rentclothes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rentclothes.databinding.TrendingnowFragmentBinding


class TrendingnowFragment: Fragment() {
    private lateinit var binding: TrendingnowFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrendingnowFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }
}
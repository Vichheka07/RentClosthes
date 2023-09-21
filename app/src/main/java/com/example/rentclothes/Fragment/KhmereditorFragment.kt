package com.example.rentclothes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rentclothes.databinding.KhemreditorFragmentBinding

class KhmereditorFragment : Fragment() {
    private lateinit var binding: KhemreditorFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = KhemreditorFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }
}
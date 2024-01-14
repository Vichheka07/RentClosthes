package com.example.rentclothes.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rentclothes.CategoriesActivity
import com.example.rentclothes.SettingActivity
import com.example.rentclothes.databinding.ProfileFragmentBinding
import com.example.rentclothes.model.SiginScreen

class ProfileFragment:Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setting.setOnClickListener{
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
    }
}
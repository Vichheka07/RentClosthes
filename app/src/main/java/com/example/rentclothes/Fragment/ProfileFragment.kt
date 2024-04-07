package com.example.rentclothes.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.Activity.SettingActivity
import com.example.rentclothes.Activity.ShopActivity
import com.example.rentclothes.databinding.ProfileFragmentBinding
import com.example.rentclothes.viewModel.SigninScreenViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var userViewModel: SigninScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(SigninScreenViewModel::class.java)

        binding.setting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
        binding.shopActivity.setOnClickListener{
            val intent = Intent(requireActivity(), ShopActivity::class.java)
            startActivity(intent)
        }

        // Observe LiveData from ViewModel
        userViewModel.SiginScreenData.observe(viewLifecycleOwner) { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(context, "Yes", Toast.LENGTH_LONG).show()
                // Make sure to finish the current activity if you want to prevent the user from coming back to the login screen using the back button
                println("kks ${resource.data}")
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(context, "Incorrect!", Toast.LENGTH_LONG).show()
            }
        }
    }
}

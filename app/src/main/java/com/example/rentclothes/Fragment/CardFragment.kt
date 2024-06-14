package com.example.rentclothes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.R
import com.example.rentclothes.adapter.CardAdapter
import com.example.rentclothes.databinding.CardFragemntBinding
import com.example.rentclothes.model.CardItem
import com.example.rentclothes.viewModel.CardScreenViewModel
import com.google.android.material.tabs.TabLayout


class CardFragment : Fragment() {
    private lateinit var binding:CardFragemntBinding
    private var viewModel = CardScreenViewModel();
    private var screen: String = "";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardFragemntBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        changeStatusBarColor(R.color.background_color_home)
        viewModel.loadCategoryScreen("customer")
        viewModel.cardscreenData.observe(viewLifecycleOwner) { resource ->
            if (resource.status == Status.SUCCESS) {
                val data = resource.data?.data
                data?.let { items ->
                    var test: List<CardItem>? = null;
                    if (test != items) {
                        test = items;
                        CardScreen(test)
                    } else {
                        CardScreen(test)
                    }
                }
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(
                    requireContext(),
                    "Error while loading data from server",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        viewModel.loadCategoryScreen("customer")
                        screen = "customer"
                    }
                    1 -> {viewModel.loadCategoryScreen("Seller")
                          screen = "seller"
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Handle tab unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Handle tab reselected
            }
        })
    }


    private fun CardScreen(itemsCard: List<CardItem>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.imageCard.layoutManager = layoutManager
        val adapter = CardAdapter();
        adapter.setUserList(itemsCard)
        adapter.addItems(screen)
        binding.imageCard.adapter =adapter

    }

    private fun changeStatusBarColor(colorResId: Int) {
        activity?.let { fragmentActivity ->
            // Check if the version is at least Lollipop (API level 21)
            val window: Window = fragmentActivity.window
            val color: Int = ContextCompat.getColor(fragmentActivity, colorResId)

            // Set the status bar color
            window.statusBarColor = color
        }
    }
}
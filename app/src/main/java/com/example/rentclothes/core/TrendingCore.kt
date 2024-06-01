package com.example.rentclothes.core

import Datum
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.adapter.ProductsAdapter
import com.example.rentclothes.databinding.HomeFragmentBinding
import com.example.rentclothes.viewModel.HomeScreenViewModel

class TrendingCore private constructor() {
    private val viewModel = HomeScreenViewModel()
    private lateinit var binding: HomeFragmentBinding
    companion object {
        private var instance: TrendingCore? = null

        fun getInstance(): TrendingCore {
            if (instance == null) {
                instance = TrendingCore()
            }
            return instance!!
        }
    }
    fun initialize(binding: HomeFragmentBinding) {
        this.binding = binding
    }
    fun changeCategory(context: LifecycleOwner) {
        // Check if binding has been initialized
        if (!this::binding.isInitialized) {
            throw UninitializedPropertyAccessException("Binding has not been initialized")
        }

        viewModel.loadHomeScreen()
        viewModel.homescreenData.observe(context) { resource ->
            if (resource.status == Status.SUCCESS) {
                val data = resource.data?.data
                data?.let { items ->
                    var test: List<Datum>? = null;
                    if(test != items){
                        test = items;
                        showProducts(test)
                    }else{
                        showProducts(test)
                    }
                }
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(AppCore.get().applicationContext, "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun showProducts(items: List<Datum>) {
        val layoutManager = GridLayoutManager(AppCore.get().applicationContext, 2)
        binding.recyclerView1.layoutManager = layoutManager

        val adapter = ProductsAdapter()
        adapter.setUserList(items)
        binding.recyclerView1.adapter = adapter
    }
}
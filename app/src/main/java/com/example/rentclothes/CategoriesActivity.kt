package com.example.rentclothes

import Datum
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.adapter.ProductsAdapter
import com.example.rentclothes.databinding.ActivityCategoriesBinding
import com.example.rentclothes.viewModel.CategoryScreenViewModel

class CategoriesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel = CategoryScreenViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.arrowBack.setOnClickListener{
            finish()
        }
        binding.icFilter.setOnClickListener{
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }
        binding.title.text = intent.getStringExtra("name")
        intent.getStringExtra("name")?.let { viewModel.loadCategoryScreen(it) };
        viewModel.categoryscreenData.observe(this){
                resource ->
            if (resource.status == Status.SUCCESS) {
                val data = resource.data?.data
                data?.let { items ->
                    showProducts(items)
                }
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(this, "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun showProducts(items: List<Datum>) {
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView2.layoutManager = layoutManager

        val adapter = ProductsAdapter()
        adapter.setUserList(items)
        binding.recyclerView2.adapter = adapter
    }
}
package com.example.rentclothes.Activity

import Datum
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.adapter.ProductsAdapter
import com.example.rentclothes.databinding.ActivitySearchBinding
import com.example.rentclothes.viewModel.SearchScreenViewModel

class SearchActivity:AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel = SearchScreenViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               viewModel.loadSearchScreen(s.toString());
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // Implement any additional logic you want to perform after text change.
            }
        })
        viewModel.searchscreenData.observe(this) { resource ->
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
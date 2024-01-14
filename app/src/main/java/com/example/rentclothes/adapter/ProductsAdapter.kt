package com.example.rentclothes.adapter

import Datum
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.databinding.ViewHolderProductsBinding
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private val dataList = ArrayList<Datum>()

    fun setUserList(userList: List<Datum>) {
        dataList.clear()
        dataList.addAll(userList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewHolderProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.apply {
            holder.binding.textMoney0.text = "${currentItem.price}$/${currentItem.day} Day"
            title.text = currentItem.title
            Size.text = "Size ${currentItem.size}"
            Picasso.get().load(currentItem.images?.get(0)?.url).into(imageUrl)

        }
    }

    class ViewHolder(val binding: ViewHolderProductsBinding) : RecyclerView.ViewHolder(binding.root)
}

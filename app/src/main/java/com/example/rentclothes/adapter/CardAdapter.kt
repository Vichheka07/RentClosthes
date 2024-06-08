package com.example.rentclothes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.databinding.ViewHolderCardBinding
import com.example.rentclothes.model.CardItems
import com.squareup.picasso.Picasso

class CardAdapter :ListAdapter<CardItems,CardAdapter.ViewHolder>(DiffCallback()){
    class DiffCallback:DiffUtil.ItemCallback<CardItems>(){
        override fun areItemsTheSame(oldItem: CardItems, newItem: CardItems): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: CardItems, newItem: CardItems): Boolean {
            TODO("Not yet implemented")
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolderCardBinding.inflate(LayoutInflater.from(parent.context),parent, true)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = getItem(position)
        holder.binding.apply {
            dayForRent.text = itemView.day
            date.text = itemView.date
            textTitle.text = itemView.title
            textMoney.text = itemView.money
            Picasso.get().load(itemView.image).into(imageCard)

        }
    }
    class ViewHolder(val binding: ViewHolderCardBinding):RecyclerView.ViewHolder(binding.root) {
    }
}
package com.example.rentclothes.adapter


import Datum
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.Activity.ProductDetailActivity
import com.example.rentclothes.databinding.ViewHolderProductsBinding
import com.example.rentclothes.model.ImageItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private val dataList = ArrayList<Datum>()
    val imageTest =ArrayList<ImageItem>();
    var onProductsClickListener: ((Int, Datum)-> Unit)?= null


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
            println("Heyy"+currentItem.images?.get(0)?.url);
        }
        holder.itemView.setOnClickListener {
            onProductsClickListener?.invoke(position, currentItem)
            val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java)
            var image = currentItem.images;
            image?.forEachIndexed { index, image ->
                println("Hele ${image.url}")
                imageTest.add(ImageItem(index.toString(),image.url))
            }
            val testJson = Gson().toJson(imageTest)

            intent.putExtra("price", currentItem.price)
            intent.putExtra("day", currentItem.day)
            intent.putExtra("title", currentItem.title)
            intent.putExtra("images",testJson)
            intent.putExtra("size", currentItem.size)
            intent.putExtra("description", currentItem.describe)
            imageTest.clear()
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(val binding: ViewHolderProductsBinding) : RecyclerView.ViewHolder(binding.root){
    }
}

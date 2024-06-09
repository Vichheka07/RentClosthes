package com.example.rentclothes.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.databinding.ViewHolderCardBinding
import com.example.rentclothes.model.CardItem
import com.example.rentclothes.model.CardItems
import com.squareup.picasso.Picasso

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val dataList = ArrayList<CardItem>()
    val imageTest =ArrayList<CardItems>();
    var onProductsClickListener: ((Int, CardItem)-> Unit)?= null

    fun setUserList(userList: List<CardItem>) {
        dataList.clear()
        dataList.addAll(userList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewHolderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.apply {
            textTitle.text = currentItem.title
            textPrice.text = currentItem.price +" $"
            textPeriod.text = "Date: "+currentItem.day+" Day";
            orderDate.text = "Status: "+currentItem.orderstatus;
            Picasso.get().load(currentItem.images?.get(0)?.url).into(imageCard)
        }
    }

    class ViewHolder(val binding: ViewHolderCardBinding) : RecyclerView.ViewHolder(binding.root){
    }
}

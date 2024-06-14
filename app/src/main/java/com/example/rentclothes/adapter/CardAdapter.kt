package com.example.rentclothes.adapter
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.Activity.RentProductActivity
import com.example.rentclothes.databinding.ViewHolderCardBinding
import com.example.rentclothes.model.CardItem
import com.example.rentclothes.model.CardItems
import com.squareup.picasso.Picasso

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val dataList = ArrayList<CardItem>()
    var onProductsClickListener: ((Int, CardItem)-> Unit)?= null
    var typeScreen: String = "";
    fun setUserList(userList: List<CardItem>) {
        dataList.clear()
        dataList.addAll(userList)
    }
    fun addItems(ScreentType: String) {
        typeScreen = ScreentType;
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
        holder.itemView.setOnClickListener{
            onProductsClickListener?.invoke(position, currentItem)
            val intent = Intent(holder.itemView.context, RentProductActivity::class.java)
            intent.putExtra("image", currentItem.images[0].url)
            intent.putExtra("title",currentItem.title);
            intent.putExtra("orderstatus",currentItem.orderstatus);
            intent.putExtra("name",currentItem.name);
            intent.putExtra("price",currentItem.price);
            intent.putExtra("day",currentItem.day);
            intent.putExtra("delivery",currentItem.delivery);
            intent.putExtra("size",currentItem.size);
            intent.putExtra("address",currentItem.address);
            intent.putExtra("orderdate",currentItem.orderdate);
            intent.putExtra("description",currentItem.describe);
            intent.putExtra("type",typeScreen)
            intent.putExtra("id",currentItem.id.toString());
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(val binding: ViewHolderCardBinding) : RecyclerView.ViewHolder(binding.root){
    }
}

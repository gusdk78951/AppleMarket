package com.hyuna.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyuna.applemarket.databinding.ItemRecyclerviewBinding
import java.text.NumberFormat

class MainAdapter(val mItems: MutableList<MainItem>) : RecyclerView.Adapter<MainAdapter.Holder>() {

    // ItemClick 인터페이스 생성, onClick 포함
    interface ItemClick {
        fun onClick(view : View, position : Int)
    }
    // itemClick 생성
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    // 리사이클러뷰(activity_main)에서 하나의 항목을 불러올 때 마다 onBindViewHolder 생성 / 홀더 리턴
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener { //클릭 이벤트 추가 부분
            itemClick?.onClick(it, position) // 아이템 클릭 시 어댑터와 MainActivity 사이의 통신
        }
        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.name.text = mItems[position].aName
        holder.location.text = mItems[position].aLocation
        holder.price.text = mItems[position].aPrice.toString()

        // NumberFormat을 이용해 price에 천단위 마다 콤마(,) 추가
        val formatter = NumberFormat.getInstance()
        val formattedPrice = formatter.format(mItems[position].aPrice)
        holder.price.text = formattedPrice
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemCount(): Int {
        return mItems.size
    }

    // 홀더 생성
    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.imageView
        val name = binding.textView
        val location = binding.textView2
        val price = binding.textView3
    }
}
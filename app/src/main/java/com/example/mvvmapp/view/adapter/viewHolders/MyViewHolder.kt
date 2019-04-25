package com.example.mvvmapp.view.adapter.viewHolders

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.models.ItemModel
import com.example.mvvmapp.view.adapter.GenericAdapter


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<ItemModel> {
    override fun onClickListener(data: ItemModel) {
        Log.d("CarView", "data: ${data.color}")
    }


    var tvItem: TextView = itemView.findViewById(R.id.tvItem)
    override fun bind(data: ItemModel) {
        tvItem.text = data.color
    }
}
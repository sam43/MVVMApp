package com.example.mvvmapp.view.adapter.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.models.BusItemModel
import com.example.mvvmapp.models.ItemModel
import com.example.mvvmapp.view.adapter.GenericAdapter

object ViewHolderFactory {

    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.car_item_layout -> CarViewHolder(view)
            R.layout.bus_item_layout -> BusViewHolder(view)
            else -> {
                CarViewHolder(view)
            }
        }
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<ItemModel> {
        var tvItem: TextView = itemView.findViewById(R.id.tvItem)
        override fun bind(data: ItemModel) {
            tvItem.text = data.color
        }
    }

    class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<BusItemModel> {
        var tvItem: TextView = itemView.findViewById(R.id.tvItemBus)
        override fun bind(data: BusItemModel) {
            tvItem.text = data.busBrand.plus("\nBus Color: ${data.busColor}")
        }
    }
}
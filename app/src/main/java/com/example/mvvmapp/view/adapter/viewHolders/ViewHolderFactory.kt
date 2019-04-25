package com.example.mvvmapp.view.adapter.viewHolders

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.models.BusItemModel
import com.example.mvvmapp.models.ItemModel
import com.example.mvvmapp.view.adapter.GenericAdapter

object ViewHolderFactory {


    fun create(view: View, viewType: Int, cxt: Context): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.car_item_layout -> CarViewHolder(view, cxt)
            R.layout.bus_item_layout -> BusViewHolder(view, cxt)
            else -> {
                CarViewHolder(view, cxt)
            }
        }
    }

    class CarViewHolder(itemView: View, private val cxt: Context) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<ItemModel> {
        override fun onClickListener(data: ItemModel) {
            Toast.makeText(cxt, "data: ${data.color}", Toast.LENGTH_SHORT).show()
            Log.d("CarView", "data: ${data.color}")
        }

        var tvItem: TextView = itemView.findViewById(R.id.tvItem)
        override fun bind(data: ItemModel) {
            tvItem.text = data.color
        }
    }

    class BusViewHolder(itemView: View, private val cxt: Context) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<BusItemModel> {
        override fun onClickListener(data: BusItemModel) {
            Toast.makeText(cxt, "data: ${data.busBrand}", Toast.LENGTH_SHORT).show()
            Log.d("CarViewBus", "data: ${data.busBrand}")
        }

        var tvItem: TextView = itemView.findViewById(R.id.tvItemBus)
        override fun bind(data: BusItemModel) {
            tvItem.text = data.busBrand.plus("\nBus Color: ${data.busColor}")
        }
    }
}
package com.example.mvvmapp.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.models.BusItemModel
import com.example.mvvmapp.models.ItemModel
import com.example.mvvmapp.view.adapter.GenericAdapter
import com.example.mvvmapp.view.adapter.viewHolders.ViewHolderFactory
import kotlinx.android.synthetic.main.layout_recyclerview.*

class ListActivity : AppCompatActivity() {

    private var listColors: ArrayList<ItemModel> = ArrayList()
    private var listBuses: ArrayList<BusItemModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_recyclerview)
        populateData()
        populateBusData()
    }

    private fun populateData() {
        var item = ItemModel("Red")
        listColors.add(item)
        item = ItemModel("Green")
        listColors.add(item)
        item = ItemModel("Blue")
        listColors.add(item)
        item = ItemModel("Yellow")
        listColors.add(item)
        item = ItemModel("Maroon")
        listColors.add(item)
        item = ItemModel("Black")
        listColors.add(item)
    }

    private fun populateBusData() {
        var item = BusItemModel("Audi", "Red")
        listBuses.add(item)
        item = BusItemModel("Mercedes Benz", "Green")
        listBuses.add(item)
        item = BusItemModel("Hundai", "Blue")
        listBuses.add(item)
        item = BusItemModel("Ford", "Yellow")
        listBuses.add(item)
        item = BusItemModel("Suzuki", "Maroon")
        listBuses.add(item)
        item = BusItemModel("Volks Wagon", "Black")
        listBuses.add(item)
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
    }

    private fun setupAdapter() {
        val myAdapter = object : GenericAdapter<Any>() {
            override fun getLayoutId(position: Int, obj: Any): Int {
                return when (obj) {
                    is ItemModel -> R.layout.car_item_layout
                    is BusItemModel -> R.layout.bus_item_layout
                    else -> R.layout.car_item_layout
                }
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.create(view, viewType)
            }
        }
        //myAdapter.setItems(listColors)
        myAdapter.setItems(listBuses)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = myAdapter
    }
}
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
import kotlin.random.Random

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
        item = BusItemModel("Mercedes A370", "Gray")
        listBuses.add(item)
        item = BusItemModel("Toyota", "Royal Blue")
        listBuses.add(item)
        item = BusItemModel("Ford 430D", "Yellow")
        listBuses.add(item)
        item = BusItemModel("Hino 345", "White")
        listBuses.add(item)
        item = BusItemModel("Royal Roach", "Black")
        listBuses.add(item)
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
    }

    private fun setupAdapter() {
        val randomVal = Random.nextInt(0, 3)
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
        if (randomVal == 1) {
            myAdapter.setItems(listColors)
        } else {
            myAdapter.setItems(listBuses)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = myAdapter
    }
}
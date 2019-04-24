package com.example.mvvmapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.interfaces.BaseRecyclerListener

/**
 * Created by Sayem.
 */
abstract class GenericAdapter<T, L : BaseRecyclerListener> : RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private var listItems: List<T> = ArrayList()
    private lateinit var listener: L

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor() {
        this.listItems = emptyList()
    }

    constructor(listen: L) {
        this.listener = listen
    }

    fun setItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                , viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T)
    }
}
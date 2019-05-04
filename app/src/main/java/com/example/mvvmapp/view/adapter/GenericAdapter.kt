package com.example.mvvmapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam43.customtextview.LoggingClass


/**
 * Created by Sayem.
 */
abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private var listItems: List<T> = ArrayList()
    private lateinit var cxt: Context

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor(context: Context) {
        this.cxt = context
        this.listItems = emptyList()
    }

    fun setItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //val holder = setViewHolder(parent, listener)
        return getViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                , viewType)
        //return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        LoggingClass.debug("value: ${listItems[position]}")
        (holder as Binder<T>).bind(listItems[position])
        holder.itemView.setOnClickListener {
            (holder as Binder<T>).onClickListener(listItems[position])
        }
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
        fun onClickListener(data: T)
    }
}
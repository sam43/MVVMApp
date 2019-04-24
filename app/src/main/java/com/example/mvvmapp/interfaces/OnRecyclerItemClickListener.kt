package com.example.mvvmapp.interfaces

import androidx.recyclerview.widget.RecyclerView


interface OnRecyclerItemClickListener : BaseRecyclerListener {

    /**
     * Returns clicked item position [RecyclerView.ViewHolder.getAdapterPosition]
     *
     * @param position clicked item position.
     */
    fun onItemClick(position: Int)
}
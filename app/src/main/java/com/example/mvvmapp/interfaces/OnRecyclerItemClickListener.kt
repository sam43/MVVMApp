package com.example.mvvmapp.interfaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView


interface OnRecyclerItemClicked {

    /**
     * Returns clicked item position [RecyclerView.ViewHolder.getAdapterPosition]
     *
     * @param position clicked item position.
     */
    fun onItemClicked(view: View, position: Int)
}
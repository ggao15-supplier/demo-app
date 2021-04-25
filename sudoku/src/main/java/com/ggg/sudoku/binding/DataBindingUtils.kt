package com.ggg.sudoku.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter


/**
 * Created by  gggao on 4/25/2021.
 */
class DataBindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("app:adapter", "app:layoutManager")
        fun <VH : RecyclerView.ViewHolder> setRecycleViewAdapter(
            view: RecyclerView,
            adapter: Adapter<VH>,
            layoutManager: RecyclerView.LayoutManager
        ) {
            view.adapter = adapter
            view.layoutManager = layoutManager
        }
    }
}
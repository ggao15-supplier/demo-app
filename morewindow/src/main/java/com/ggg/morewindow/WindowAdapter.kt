package com.ggg.morewindow

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WindowAdapter : RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class WindowViewHolder(private val view: WindowView) : RecyclerView.ViewHolder(view) {
        fun bind(data: WebData) {
            view.load(data.url)
        }
    }
}
package com.ggg.sudoku.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by  gggao on 4/25/2021.
 */
class SudokuViewModel : ViewModel(), LifecycleObserver {
    val tableAdapterObservable: ObservableField<RecyclerView.Adapter<in RecyclerView.ViewHolder>?> =
        ObservableField()
    val tableLayoutManagerObservable: ObservableField<RecyclerView.LayoutManager?> =
        ObservableField()
    val inputAdapterObservable: ObservableField<RecyclerView.Adapter<in RecyclerView.ViewHolder>?> =
        ObservableField()
    val inputLayoutManagerObservable: ObservableField<RecyclerView.LayoutManager?> =
        ObservableField()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }
}
package com.ggg.sudoku.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


/**
 * Created by  gggao on 4/28/2021.
 */
class TableItemViewModel : ViewModel() {
    companion object {
        enum class TableState {
            normal, same
        }
    }

    private val tableItemObserver: ObservableField<String?> = ObservableField()
    private val tableItemColorObserver: ObservableField<Int> =
        ObservableField(android.R.color.black)

    fun updateValue(value: String?) {
        tableItemObserver.set(value)
    }
    fun updateState(state: TableState) {
        when (state) {
            TableState.normal -> tableItemColorObserver.set(android.R.color.black)
            TableState.same -> tableItemColorObserver.set(android.R.color.holo_red_light)
        }

    }
}
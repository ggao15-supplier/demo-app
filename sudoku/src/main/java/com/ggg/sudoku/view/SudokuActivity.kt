package com.ggg.sudoku.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ggg.sudoku.databinding.ActivitySudokuBinding
import com.ggg.sudoku.viewmodel.SudokuViewModel


/**
 * Created by  gggao on 4/25/2021.
 */
class SudokuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivitySudokuBinding = ActivitySudokuBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.viewModel = SudokuViewModel()
        lifecycle.addObserver(bind.viewModel)
        bind.viewModel.tableLayoutManagerObservable.set(GridLayoutManager(this, 81))
        bind.viewModel.inputLayoutManagerObservable.set(GridLayoutManager(this, 9))
    }
}
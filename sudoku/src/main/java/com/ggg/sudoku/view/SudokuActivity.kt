package com.ggg.sudoku.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ggg.sudoku.R
import com.ggg.sudoku.databinding.ActivitySudokuBinding
import com.ggg.sudoku.viewmodel.SudokuViewModel


/**
 * Created by  gggao on 4/25/2021.
 */
class SudokuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val bind: ActivitySudokuBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sudoku)
        val viewModel = SudokuViewModel()
        lifecycle.addObserver(viewModel)
        viewModel.tableLayoutManagerObservable.set(GridLayoutManager(this, 81))
        viewModel.inputLayoutManagerObservable.set(GridLayoutManager(this, 9))
        bind.viewModel = viewModel
    }
}
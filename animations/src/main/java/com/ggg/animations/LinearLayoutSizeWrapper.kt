package com.ggg.animations

import android.widget.LinearLayout

class LinearLayoutSizeWrapper(private val view: LinearLayout) {

    fun setWidth(width: Int) {
        view.layoutParams.width = width
        view.requestLayout()
    }

    fun getWidth(): Int = view.layoutParams.width

    fun setHeight(height: Int) {
        view.layoutParams.height = height
        view.requestLayout()
    }

    fun getHeight(): Int = view.layoutParams.height
}
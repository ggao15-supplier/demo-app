package com.fprdmps.mobileappcn.windowmanagers

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import androidx.core.content.ContextCompat

class FloatButtonWindowManager(
    private val windowManager: WindowManager,
    private val context: Context
) {
    private val layoutParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
        200,
        200,
        TYPE_APPLICATION,
        FLAG_NOT_FOCUSABLE,
        PixelFormat.RGBA_8888
    )
    private val button = FloatButton(context)

    init {
        addView()

    }

    private fun addView() {
        button.setPadding(50, 50, 50, 50)
        button.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        button.setImageResource(android.R.drawable.button_onoff_indicator_off)
        //必须设置为这个，否则会出现 touch 事件中 rawX/rawY为整个屏幕的宽高问题
        layoutParams.gravity = Gravity.TOP or Gravity.START
        layoutParams.x = 400
        layoutParams.y = 400
        windowManager.addView(button, layoutParams)
        button.listener = object : OnUpdateLayoutListener {
            override fun onUpdateLayout(x: Int, y: Int) {
                Log.d("xxx", "x:$x;;y:$y")
                layoutParams.x = x
                layoutParams.y = y
                windowManager.updateViewLayout(button, layoutParams)
            }

        }
    }

    interface OnUpdateLayoutListener {
        fun onUpdateLayout(x: Int, y: Int)
    }
}
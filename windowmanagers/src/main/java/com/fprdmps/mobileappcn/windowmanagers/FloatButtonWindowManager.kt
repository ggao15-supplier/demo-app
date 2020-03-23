package com.fprdmps.mobileappcn.windowmanagers

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.widget.ImageButton

class FloatButtonWindowManager(
    private val windowManager: WindowManager,
    context: Context
) {
    private val layoutParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
        WRAP_CONTENT,
        WRAP_CONTENT,
        TYPE_APPLICATION,
        FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSPARENT
    )
    private val button = ImageButton(context)

    init {
        addView()
    }

    private fun addView() {
        button.setPadding(50, 50, 50, 50)
        button.setBackgroundColor(Color.BLUE)
        button.setImageResource(android.R.drawable.stat_notify_sync_noanim)
        layoutParams.gravity = Gravity.CENTER or Gravity.END
        windowManager.addView(button, layoutParams)
        button.setOnTouchListener(initTouchListener())
    }

    private fun initTouchListener(): View.OnTouchListener {
        return View.OnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    val x = event.rawX
                    val y = event.rawY
                    Log.d("xxx","x:$x;;y:$y")
                    layoutParams.x = x.toInt()
                    layoutParams.y = y.toInt()
                    windowManager.updateViewLayout(button, layoutParams)
                }
            }
            return@OnTouchListener false
        }
    }
}
package com.fprdmps.mobileappcn.windowmanagers

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageButton

class FloatButton(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) :
    ImageButton(context, attrs, defStyleAttr) {
    private var halfW: Int = 0
    private var halfH: Int = 0

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(context: Context) : this(
        context,
        null
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        halfH = 3*MeasureSpec.getSize(heightMeasureSpec)/4
        halfW = MeasureSpec.getSize(widthMeasureSpec) / 2
    }

    var listener: FloatButtonWindowManager.OnUpdateLayoutListener? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = (event?.rawX?.minus(halfW)) ?: 0f
        val y = (event?.rawY?.minus(halfH)) ?: 0f
        Log.d(
            "xxx",
            "float::rawx:${event?.rawX};;;rawy:${event?.rawY};;halfW:$halfW;;halfH:$halfH"
        )
        listener?.onUpdateLayout(x.toInt(), y.toInt())
        return super.onTouchEvent(event)
    }
}
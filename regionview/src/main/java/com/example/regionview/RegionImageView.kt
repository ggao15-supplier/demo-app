package com.example.regionview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView

class RegionImageView(context: Context, attrs: AttributeSet?, defaultStyle: Int) :
    ImageView(context, attrs, defaultStyle) {
    private val rect: Rect = Rect()

    private val gestureDetector: GestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return super.onScroll(e1, e2, distanceX, distanceY)
            }

        })
    private val scaleGestureDetector: ScaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {

                return true
            }
        })
    private var regionDecoder: BitmapRegionDecoder? = null

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    fun fromAssets(assetPath: String) {
        val inputStream = context.assets.open(assetPath)
        regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
        init()
    }

    fun fromNetWork(path: String) {
        init()
    }

    fun fromFile(path: String) {
        regionDecoder = BitmapRegionDecoder.newInstance(path, false)
        init()
    }

    private fun init() {
        rect.set(0, 0, regionDecoder?.width ?: 0, regionDecoder?.height ?: 0)
        setImageBitmap(regionDecoder?.decodeRegion(rect, BitmapFactory.Options()))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event) || scaleGestureDetector.onTouchEvent(event)
    }
}
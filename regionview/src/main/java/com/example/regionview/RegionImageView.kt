package com.example.regionview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.scale
import kotlin.math.abs

class RegionImageView(context: Context, attrs: AttributeSet?, defaultStyle: Int) :
    View(context, attrs, defaultStyle) {
    private val rect: Rect = Rect()
    private var bitmapWidth = 0
    private var bitmapHeight = 0
    private var nowBitmapWidth = 0
    private var nowBitmapHeight = 0
    private var scale = 1f
    private var isScale = false
    private var newBitmap: Bitmap? = null
    private val option = BitmapFactory.Options()
    private val gestureDetector: GestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return if (!isScale) {
                    val downX: Float = e1?.x ?: 0f
                    val nowX: Float = e2?.x ?: 0f
                    val downY: Float = e1?.y ?: 0f
                    val nowY: Float = e1?.y ?: 0f
                    move((nowX - downX - distanceX).toInt(), (nowY - downY - distanceY).toInt())
                    true
                } else {
                    super.onScroll(e1, e2, distanceX, distanceY)
                }
            }
        })

    private val scaleGestureDetector: ScaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                scale *= (detector?.scaleFactor ?: 1f)//当前缩放比例 * 这次缩放的缩放比例的比例
                Log.d("xxx", "scale::$scale")
                createBitMap()
                return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                isScale = true
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {
                isScale = false
                super.onScaleEnd(detector)
            }
        })
    private var regionDecoder: BitmapRegionDecoder? = null

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    fun fromAssets(assetPath: String) {
        val inputStream = context.assets.open(assetPath)
        option.inJustDecodeBounds = true

        BitmapFactory.decodeStream(inputStream, null, option)
        bitmapHeight = option.outHeight
        bitmapWidth = option.outWidth
        nowBitmapHeight = bitmapHeight
        nowBitmapWidth = bitmapWidth
        option.inJustDecodeBounds = false
        regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
        init()
    }

    fun fromNetWork(path: String) {
        init()
    }

    fun fromFile(path: String) {
        option.inJustDecodeBounds = true
        val b: Bitmap? = BitmapFactory.decodeFile(path, option)
        bitmapHeight = b?.height ?: 0
        bitmapWidth = b?.width ?: 0
        nowBitmapHeight = bitmapHeight
        nowBitmapWidth = bitmapWidth
        option.inJustDecodeBounds = false
        regionDecoder = BitmapRegionDecoder.newInstance(path, false)
        init()
    }

    private fun init() {
        Log.d(
            "xxx",
            "width:$width;;height:$height;;bitmapWidth:$bitmapWidth;;bitmpaHeight::$bitmapHeight"
        )
        var left = 0
        var top = 0
        if (bitmapWidth > width) {
            left = (bitmapWidth - width) / 2
        }
        if (bitmapHeight > height) {
            top = (bitmapHeight - height) / 2
        }
        rect.set(left, top, left + width, top + height)
        createBitMap()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        newBitmap?.apply {
            canvas?.drawBitmap(this, 0f, 0f, null)
        }
    }

    private fun createBitMap() {
        regionDecoder?.decodeRegion(rect, BitmapFactory.Options())?.apply {
            Log.d("xxx", "bitmap:w:${this.width};;h:${this.height}")
            val mMatrix = Matrix()
            mMatrix.postScale(scale, scale)
            newBitmap?.recycle()
            newBitmap = Bitmap.createBitmap(
                this,
                0,
                0,
                this.width,
                this.height,
                mMatrix,
                true
            )
            nowBitmapWidth =
                if (newBitmap?.width ?: 0 > bitmapWidth) newBitmap?.width ?: 0 else bitmapWidth
            nowBitmapHeight =
                if (newBitmap?.height ?: 0 > bitmapHeight) newBitmap?.height ?: 0 else bitmapHeight
            invalidate()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event) || scaleGestureDetector.onTouchEvent(event)
    }

    private fun move(dx: Int, dy: Int) {
        Log.d("xxx", "dx:$dx;;dy::$dy")
        if (abs(dx) > 0 && nowBitmapWidth > width) {
            when {
                rect.left < 0 -> {
                    Log.d("xxx", " rect.left:${rect.left}")
//
                    rect.left = 0
                    rect.right = width
                    return
                }
                rect.right > nowBitmapWidth -> {
                    Log.d("xxx", " rect.right:${rect.right}")
//
                    rect.right = nowBitmapWidth
                    rect.left = nowBitmapWidth - width
                    return
                }
                else -> {
                    Log.d("xxx", "ssss")
                    rect.offset(-dx / 10, 0)
                }
            }
            createBitMap()
        }

        if (abs(dy) > 0 && nowBitmapHeight > height) {
            when {
                rect.top < 0 -> {
                    rect.top = 0
                    rect.bottom = height
                    return
                }
                rect.bottom > nowBitmapHeight -> {
//                    rect.bottom = bitmapHeight
//                    rect.top = bitmapHeight - height
                    return
                }
                else -> {
                    rect.offset(0, -dy / 10)
                }
            }
            createBitMap()

        }

    }
}
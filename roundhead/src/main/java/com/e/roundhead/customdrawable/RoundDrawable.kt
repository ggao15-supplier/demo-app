package com.e.roundhead.customdrawable

import android.graphics.*
import android.graphics.drawable.Drawable

class RoundDrawable(private val srcBitmap: Bitmap) : Drawable() {
    private val paint = Paint()
    override fun draw(canvas: Canvas) {
        val width = srcBitmap.width
        val height = srcBitmap.height
        canvas.drawColor(Color.TRANSPARENT)
        
        //重置cavas的layer ，将其背景奢望透明
        var layerId = canvas.saveLayer(
            0f,
            0f,
            canvas.width.toFloat(),
            canvas.height.toFloat(),
            null)
        paint.color = Color.WHITE
        val radius: Float = if (width > height) (height / 2).toFloat() else (width / 2).toFloat()
        canvas.drawCircle(radius, radius, radius-20, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(srcBitmap, 0f, 0f, paint)
        paint.xfermode = null
        //将layerId的layer设置为默认的layer
        canvas.restoreToCount(layerId)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
        invalidateSelf()
    }

}
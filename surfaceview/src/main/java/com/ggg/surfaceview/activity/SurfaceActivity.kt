package com.ggg.surfaceview.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ggg.surfaceview.R
import kotlinx.android.synthetic.main.activity_surface.*


/**
 * Created by  gggao on 3/19/2021.
 */
class SurfaceActivity : AppCompatActivity() {
    private var callback: SurfaceHolder.Callback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface)
        callback = object :
            SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                val paint = Paint()
                paint.isAntiAlias = true
                paint.style = Paint.Style.STROKE

                loadBitmap()?.apply {
                    val canvas = holder.lockCanvas()
                    canvas.drawBitmap(
                        this,
                        (width / 2).toFloat(),
                        (height / 2).toFloat(),
                        paint
                    )
                    holder.unlockCanvasAndPost(canvas)
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {

            }

        }
        imageSurface.holder.addCallback(callback)

    }

    override fun onDestroy() {
        super.onDestroy()
        callback?.apply { imageSurface.holder.removeCallback(this) }

    }

    /**
     * 4.4 以后,直接加载vector,bitmap 会为null,需要使用 drawable 加载,然后使用 canvas 将vector draw 到 bitmap上
     */
    private fun loadBitmap(): Bitmap? {
        val drawable = ContextCompat.getDrawable(
            this@SurfaceActivity,
            R.drawable.ic_baseline_fingerprint_24
        )
        drawable?.apply {
            val bitmap = Bitmap.createBitmap(
                intrinsicWidth,
                intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val bitmapCanvas = Canvas(bitmap)
            setBounds(0, 0, bitmapCanvas.width, bitmapCanvas.height)
            draw(bitmapCanvas)

            return bitmap
        }
        return null
    }
}
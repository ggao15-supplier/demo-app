package com.ggg.renderscript.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.ggg.renderscript.R
import com.ggg.renderscript.databinding.FragmentDynamicGaussianBlurBinding
import com.ggg.renderscript.databinding.FragmentGaussianBlurBinding


/**
 * Created by  gggao on 5/14/2021.
 */
class DynamicGaussianBlurFragment : Fragment() {
    private var bind: FragmentDynamicGaussianBlurBinding? = null
    private var holderPreview: SurfaceHolder? = null
    private val paint = Paint()
    private var previewWidth: Float = 0f
    private var previewHeight: Float = 0f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        bind = FragmentDynamicGaussianBlurBinding.inflate(inflater, container, false)
        bind?.sfOriginal?.holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                previewHeight = height.toFloat()
                previewWidth = width.toFloat()
                holderPreview = holder

                draw(BitmapFactory.decodeResource(resources, R.mipmap.test_blur))

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

            override fun surfaceCreated(holder: SurfaceHolder) {
            }

        })
        bind?.seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                turn(progress.toFloat() / 100.0f)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        return bind?.root
    }

    private fun draw(bitmap: Bitmap) {
        holderPreview?.apply {
            val canvas = lockCanvas()
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            unlockCanvasAndPost(canvas)
        }
    }

    private fun turn(radius: Float) {
        Log.d("xxx", "radius:$radius")
        val rs: RenderScript = RenderScript.create(context)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test_blur)
        val allocation: Allocation = Allocation.createFromBitmap(rs, bitmap)
        val type = allocation.type
        val blurAllocation = Allocation.createTyped(rs, type)
        val blurScript: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        blurScript.setRadius(radius * 25.0f)
        blurScript.setInput(allocation)
        blurScript.forEach(blurAllocation)

        blurAllocation.copyTo(bitmap)
        draw(bitmap)
        allocation.destroy()
        blurAllocation.destroy()
        blurScript.destroy()
        rs.destroy()
    }
}
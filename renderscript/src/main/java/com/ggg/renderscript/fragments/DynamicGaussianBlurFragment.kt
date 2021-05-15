package com.ggg.renderscript.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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


/**
 * Created by  gggao on 5/14/2021.
 */
class DynamicGaussianBlurFragment : Fragment() {
    private var bind: FragmentDynamicGaussianBlurBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentDynamicGaussianBlurBinding.inflate(inflater, container, false)
        bind?.ivOriginal?.setImageResource(R.mipmap.test_blur)
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
        bind?.ivOriginal?.setImageBitmap(bitmap)
        allocation.destroy()
        blurAllocation.destroy()
        blurScript.destroy()
        rs.destroy()
    }
}
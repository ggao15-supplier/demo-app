package com.ggg.renderscript.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.ggg.renderscript.R
import com.ggg.renderscript.databinding.FragmentGaussianBlurBinding


/**
 * Created by  gggao on 5/14/2021.
 */
class GaussianBlurFragment : Fragment() {
    private var bind: FragmentGaussianBlurBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentGaussianBlurBinding.inflate(inflater, container, false)
        bind?.ivOriginal?.setImageResource(R.mipmap.test_blur)
        bind?.btnTurn?.setOnClickListener { turn() }
        return bind?.root
    }

    private fun turn() {
        val rs: RenderScript = RenderScript.create(context)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test_blur)
        val allocation: Allocation = Allocation.createFromBitmap(rs, bitmap)
        val type = allocation.type
        val blurAllocation = Allocation.createTyped(rs, type)
        val blurScript: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        blurScript.setRadius(24.5f)
        blurScript.setInput(allocation)
        blurScript.forEach(blurAllocation)

        blurAllocation.copyTo(bitmap)
        bind?.ivResult?.setImageBitmap(bitmap)
        allocation.destroy()
        blurAllocation.destroy()
        blurScript.destroy()
        rs.destroy()
    }
}
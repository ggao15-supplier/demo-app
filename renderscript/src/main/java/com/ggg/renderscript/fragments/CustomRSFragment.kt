package com.ggg.renderscript.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.renderscript.Allocation
import androidx.renderscript.RenderScript
import com.ggg.renderscript.R
import com.ggg.renderscript.ScriptC_test
import com.ggg.renderscript.databinding.FragmentCustomSrBinding


/**
 * Created by  gggao on 5/14/2021.
 */
class CustomRSFragment : Fragment() {
    private var bind: FragmentCustomSrBinding? = null
    private val rs: RenderScript by lazy { RenderScript.create(context) }
    private val scriptCTest: ScriptC_test by lazy { ScriptC_test(rs) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCustomSrBinding.inflate(inflater, container, false)
        bind?.ivOriginal?.setImageResource(R.mipmap.test_blur)
        bind?.btnInvert?.setOnClickListener { invert() }
        bind?.btnGreyScale?.setOnClickListener { greyScale() }
        bind?.btnInvertAndGreyScale?.setOnClickListener { both() }
        return bind?.root
    }

    private fun invert() {
        val rs: RenderScript = RenderScript.create(context)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test_blur)
        val allocation = Allocation.createFromBitmap(rs, bitmap)
        val outAllocation = Allocation.createTyped(rs, allocation.type)
        scriptCTest.forEach_invert(allocation, outAllocation)
        outAllocation.copyTo(bitmap)
        bind?.ivInvertResult?.setImageBitmap(bitmap)
        destroy(allocation, outAllocation)
    }

    private fun greyScale() {
        val rs: RenderScript = RenderScript.create(context)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test_blur)
        val allocation = Allocation.createFromBitmap(rs, bitmap)
        val outAllocation = Allocation.createTyped(rs, allocation.type)
        scriptCTest.forEach_greyScale(allocation, outAllocation)
        outAllocation.copyTo(bitmap)
        bind?.ivGreyScaleResult?.setImageBitmap(bitmap)
        destroy(allocation, outAllocation)
    }

    private fun both() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test_blur)
        val allocation = Allocation.createFromBitmap(rs, bitmap)
        val outAllocation = Allocation.createTyped(rs, allocation.type)
        scriptCTest.invoke_process(allocation, outAllocation)
        outAllocation.copyTo(bitmap)
        bind?.ivInvertAndGreyScaleResult?.setImageBitmap(bitmap)

        destroy(allocation, outAllocation)
    }

    private fun destroy(allocation: Allocation, outAllocation: Allocation) {
        allocation.destroy()
        outAllocation.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scriptCTest.destroy()
        rs.destroy()

    }
}
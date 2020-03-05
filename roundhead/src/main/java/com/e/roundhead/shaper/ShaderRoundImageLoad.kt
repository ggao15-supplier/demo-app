package com.e.roundhead.shaper

import android.graphics.*
import com.e.roundhead.RoundImageLoad

class ShaderRoundImageLoad : RoundImageLoad {

    override fun handleImage(srcImage: Bitmap): Bitmap {
        val paint = Paint()
        val destImage = Bitmap.createBitmap(srcImage.width, srcImage.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(destImage)
        val radius = if (destImage.width < destImage.height) destImage.width / 2 else destImage.height / 2

        val shader = BitmapShader(srcImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat() - 20, paint)
        return destImage
    }

}
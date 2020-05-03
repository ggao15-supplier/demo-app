package com.ggg.roundhead.xfmodel

import android.graphics.*
import com.ggg.roundhead.RoundImageLoad

class XFModelRoundImageLoad : RoundImageLoad {
    override fun handleImage(srcImage: Bitmap): Bitmap {
        val destImage: Bitmap = Bitmap.createBitmap(srcImage.width, srcImage.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(destImage)
        canvas.drawColor(Color.TRANSPARENT)
        val radius = if (destImage.width > destImage.height) destImage.width / 2 else destImage.height / 2
        val paint = Paint()
        paint.color = Color.WHITE
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat() - 20, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        val src = Rect(0, 0, srcImage.width, srcImage.height)
        val dest = Rect(0, 0, destImage.width, destImage.height)
        canvas.drawBitmap(srcImage, src, dest, paint)

        return destImage
    }

}
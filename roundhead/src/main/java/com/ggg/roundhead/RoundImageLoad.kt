package com.ggg.roundhead

import android.graphics.Bitmap

interface RoundImageLoad {
    fun handleImage(srcImage: Bitmap): Bitmap
}
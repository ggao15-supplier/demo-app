package com.e.roundhead

import android.graphics.Bitmap

interface RoundImageLoad {
    fun handleImage(srcImage: Bitmap): Bitmap
}
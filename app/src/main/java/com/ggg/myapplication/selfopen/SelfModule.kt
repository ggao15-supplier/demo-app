package com.ggg.myapplication.selfopen

import android.content.Context
import android.content.Intent
import com.ggg.audio.activity.AudioActivity
import com.ggg.surfaceview.activity.SurfaceActivity


/**
 * Created by  gggao on 3/19/2021.
 */
enum class SelfModule(val value: String) {
    SurfaceView("surfaceView"),
    Audio("audio");

    fun openSelfModule(context: Context) {
        when (this) {
            SurfaceView -> {
                context.startActivity(Intent(context, SurfaceActivity::class.java))
            }
            Audio -> {
                context.startActivity(Intent(context, AudioActivity::class.java))
            }
        }

    }
}
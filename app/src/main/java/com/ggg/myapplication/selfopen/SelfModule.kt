package com.ggg.myapplication.selfopen

import android.content.Context
import android.content.Intent
import com.ggg.surfaceview.activity.SurfaceActivity


/**
 * Created by  gggao on 3/19/2021.
 */
enum class SelfModule(val value: String) {
    SurfaceView("surfaceView");

    fun openSelfModule(context: Context) {
        when (this) {
            SurfaceView -> {
                context.startActivity(Intent(context, SurfaceActivity::class.java))
            }
        }

    }
}
package com.ggg.myapplication.selfopen

import android.content.Context
import android.content.Intent
import com.ggg.audio.activity.AudioActivity
import com.ggg.custombinder.client.ClientActivity
import com.ggg.jniutils.activity.JNIUtilActivity
import com.ggg.messager.client.MessengerClientActivity
import com.ggg.renderscript.RendScriptDemoActivity
import com.ggg.surfaceview.activity.SurfaceActivity


/**
 * Created by  gggao on 3/19/2021.
 */
enum class SelfModule(val value: String) {
    SurfaceView("surfaceView"),
    Aidl("aidl"),
    Messenger("Messenger"),
    Audio("audio"),
    RenderScript("RenderScript"),
    JNIUtils("JNIUtils");

    fun openSelfModule(context: Context) {
        when (this) {
            SurfaceView -> {
                context.startActivity(Intent(context, SurfaceActivity::class.java))
            }
            Audio -> {
                context.startActivity(Intent(context, AudioActivity::class.java))
            }
            Aidl -> {
                context.startActivity(Intent(context, ClientActivity::class.java))
            }
            Messenger -> {
                context.startActivity(Intent(context, MessengerClientActivity::class.java))
            }
            JNIUtils -> {
                context.startActivity(Intent(context, JNIUtilActivity::class.java))
            }

            else -> {

            }
        }

    }
}
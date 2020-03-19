package com.e.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.remoteviews.service.PlayerService
import com.e.roundhead.RoundImageLoad
import com.e.roundhead.shaper.ShaderRoundImageLoad
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, PlayerService::class.java))
        val shaderRoundImageLoad: RoundImageLoad = ShaderRoundImageLoad()
        ivHeadLeft.setImageBitmap(
            shaderRoundImageLoad.handleImage(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher
                )
            )
        )
//        val xfModelRoundImageLoad = XFModelRoundImageLoad()
//
//        ivHeadRight.setImageBitmap(
//            xfModelRoundImageLoad.handleImage(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.ic_launcher
//                )
//            )
//        )
    }
}

package com.ggg.myapplication.views.login

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ggg.myapplication.views.home.MainActivity
import com.e.myapplication.R
import com.ggg.remoteviews.service.PlayerService
import com.ggg.roundhead.RoundImageLoad
import com.ggg.roundhead.shaper.ShaderRoundImageLoad
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
        btnEnter.setOnClickListener {
            val translationDrawable: TransitionDrawable = btnEnter.background as TransitionDrawable
            translationDrawable.startTransition(300)
            handler.postDelayed({
                MainActivity.enter(this@LoginActivity)
            }, 500)
        }
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

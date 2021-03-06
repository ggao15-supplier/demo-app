package com.ggg.myapplication.views.login

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ggg.myapplication.views.home.MainActivity
import com.e.myapplication.R
import com.e.myapplication.databinding.ActivityLoginBinding
import com.ggg.myapplication.selfopen.SelfModule
import com.ggg.remoteviews.service.PlayerService
import com.ggg.roundhead.RoundImageLoad
import com.ggg.roundhead.shaper.ShaderRoundImageLoad
import com.ggg.roundhead.xfmodel.XFModelRoundImageLoad

class LoginActivity : AppCompatActivity() {
    private val handler: Handler = Handler()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(Intent(this, PlayerService::class.java))
        val shaderRoundImageLoad: RoundImageLoad = ShaderRoundImageLoad()
        binding.ivHeadLeft.setImageBitmap(
            shaderRoundImageLoad.handleImage(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher
                )
            )
        )
        binding.btnEnter.setOnClickListener {
            val translationDrawable: TransitionDrawable =
                binding.btnEnter.background as TransitionDrawable
            translationDrawable.startTransition(300)
            handler.postDelayed({
                MainActivity.enter(this@LoginActivity)
            }, 500)
        }
        val xfModelRoundImageLoad = XFModelRoundImageLoad()

        turn2Bitmap(R.mipmap.ic_launcher)?.apply {
            binding.ivHeadRight.setImageBitmap(
                xfModelRoundImageLoad.handleImage(this)
            )
        }

    }

    private fun turn2Bitmap(resId: Int): Bitmap? {
        val vectorDrawable = ContextCompat.getDrawable(this, resId)
        return vectorDrawable?.let {
            val temp = Bitmap.createBitmap(
                it.intrinsicWidth,
                it.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(temp)

            it.setBounds(0, 0, canvas.width, canvas.height)
            it.draw(canvas)
            return temp
        }
    }

}

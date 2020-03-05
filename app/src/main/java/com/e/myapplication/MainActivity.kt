package com.e.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.roundhead.RoundImageLoad
import com.e.roundhead.shaper.ShaderRoundImageLoad
import com.e.roundhead.xfmodel.XFModelRoundImageLoad
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shaderRoundImageLoad: RoundImageLoad = ShaderRoundImageLoad()

        ivHeadLeft.setImageBitmap(
            shaderRoundImageLoad.handleImage(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher
                )
            )
        )
        val xfModelRoundImageLoad = XFModelRoundImageLoad()

        ivHeadRight.setImageBitmap(
            xfModelRoundImageLoad.handleImage(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher
                )
            )
        )
    }
}

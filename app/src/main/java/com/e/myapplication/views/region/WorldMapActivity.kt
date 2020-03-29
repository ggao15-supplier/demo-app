package com.e.myapplication.views.region

import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.e.myapplication.R
import kotlinx.android.synthetic.main.activity_map.*
import java.io.InputStream

class WorldMapActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val inputStream: InputStream = assets.open("ic_world_map.jpg")
        val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, true)
        ivMap.setImageBitmap(
            bitmapRegionDecoder.decodeRegion(
                Rect(
                    0,
                    0,
                    ivMap.width,
                    ivMap.height
                ), BitmapFactory.Options()
            )
        )
    }
}
package com.e.myapplication.views.region

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.e.myapplication.R
import kotlinx.android.synthetic.main.activity_map.*

class WorldMapActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }
    //view 完全加载到activity中会调用
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("xxx","onWindowFocusChanged:$hasFocus")
        if (hasFocus) {
            ivMap.fromAssets("ic_world_map.jpg")
        }
    }
}
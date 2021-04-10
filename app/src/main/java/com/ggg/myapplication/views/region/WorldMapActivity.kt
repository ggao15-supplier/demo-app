package com.ggg.myapplication.views.region

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.e.myapplication.databinding.ActivityMapBinding


class WorldMapActivity : ComponentActivity() {
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //view 完全加载到activity中会调用
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("xxx", "onWindowFocusChanged:$hasFocus")
        if (hasFocus) {
            binding.ivMap.fromAssets("ic_world_map.jpg")
        }
    }
}
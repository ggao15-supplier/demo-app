package com.ggg.renderscript

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ggg.renderscript.databinding.ActivityRenderScriptDemoBinding
import com.google.android.material.tabs.TabItem


/**
 * Created by  gggao on 5/14/2021.
 */
class RendScriptDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivityRenderScriptDemoBinding =
            ActivityRenderScriptDemoBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.viewPager.adapter=RenderScriptAdapter(supportFragmentManager)
        bind.tabLayout.setupWithViewPager(bind.viewPager)
    }
}
package com.ggg.myapplication.views.home

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ggg.animations.AnimationController
import com.e.myapplication.R
import com.e.myapplication.databinding.ActivityMainBinding
import com.ggg.myapplication.views.region.WorldMapActivity
import com.ggg.roundhead.customdrawable.RoundDrawable
import com.ggg.morewindow.MoreWindowActivity
import com.ggg.myapplication.selfopen.SelfModule
import com.ggg.windowmanagers.FloatButtonWindowManager

class MainActivity : AppCompatActivity() {
    companion object {
        fun enter(context: AppCompatActivity) {
            context.startActivity(Intent(context, MainActivity::class.java))
            context.overridePendingTransition(R.anim.enter_anim, R.anim.exist_anim)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val adapter: MusicAdapter = MusicAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        binding.ivHead.setImageDrawable(
            RoundDrawable(
                BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.ic_launcher
                )
            )
        )
        AnimationController.setListAnimation(binding.rvMusics)
        binding.rvMusics.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvMusics.adapter = adapter
        setData()
        FloatButtonWindowManager(windowManager, this).onFloatClickListener =
            object : FloatButtonWindowManager.OnFloatClickListener {
                override fun onFloatClick() {
                    startActivity(Intent(this@MainActivity, WorldMapActivity::class.java))
                }

            }
        binding.ivHead.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MoreWindowActivity::class.java
                )
            )
        }
    }

    private fun setData() {
        adapter.setData(
            listOf(
                MusicItem(
                    "God is girl",
                    SelfModule.SurfaceView.value,
                    module = SelfModule.SurfaceView
                ),
                MusicItem(
                    "God is girl", SelfModule.Audio.value,
                    module = SelfModule.Audio
                ),
                MusicItem(
                    "God is girl", SelfModule.Aidl.value,
                    module = SelfModule.Aidl
                ),
                MusicItem(
                    "God is girl", SelfModule.Messenger.value,
                    module = SelfModule.Messenger
                ),
                MusicItem(
                    "God is girl", SelfModule.RenderScript.value,
                    module = SelfModule.RenderScript
                ),
                MusicItem("God is girl", SelfModule.JNIUtils.value,
                    module = SelfModule.JNIUtils),
                MusicItem("God is girl", "Crystal"),
                MusicItem("God is girl", "Crystal"),
                MusicItem("God is girl", "Crystal")
            )
        )
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        //该方法再Activity中的attach方法中执行，执行之后　初始化　phonewindow

    }

    override fun onContentChanged() {
        super.onContentChanged()
        //activity的视图添加到decodeview中的完成时调用这个方法

    }

    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        //在这个方法中decodeview　设置为可见
        Toast.makeText(this, "11", Toast.LENGTH_LONG).show()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.enter_anim, R.anim.exist_anim)
    }
}

package com.e.myapplication.views.home

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.animations.AnimationController
import com.e.myapplication.R
import com.e.myapplication.views.region.WorldMapActivity
import com.e.roundhead.customdrawable.RoundDrawable
import com.fprdmps.mobileappcn.windowmanagers.FloatButtonWindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        fun enter(context: AppCompatActivity) {
            context.startActivity(Intent(context, MainActivity::class.java))
            context.overridePendingTransition(R.anim.enter_anim, R.anim.exist_anim)
        }
    }

    private val adapter: MusicAdapter = MusicAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        ivHead.setImageDrawable(
            RoundDrawable(
                BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.ic_launcher
                )
            )
        )
        AnimationController.setListAnimation(rvMusics)
        rvMusics.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMusics.adapter = adapter
        setData()
        FloatButtonWindowManager(windowManager, this).onFloatClickListener =
            object : FloatButtonWindowManager.OnFloatClickListener {
                override fun onFloatClick() {
                    startActivity(Intent(this@MainActivity, WorldMapActivity::class.java))
                }

            }
    }

    private fun setData() {
        adapter.setData(
            listOf(
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", ""),
                MusicItem("God is girl", "Crystal", "")
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

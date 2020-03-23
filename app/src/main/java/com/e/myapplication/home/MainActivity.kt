package com.e.myapplication.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.animations.AnimationController
import com.e.myapplication.R
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
        FloatButtonWindowManager(windowManager, this)
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.enter_anim, R.anim.exist_anim)
    }
}

package com.e.animations

import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView

class AnimationController {
    companion object {
        fun setListAnimation(listView: RecyclerView) {
            //            android:layoutAnimation="@anim/layout_animations" ,在 recyclerview的属性中添加
            val animation = AnimationUtils.loadAnimation(listView.context, R.anim.anim_list_enter)
            val layoutAnimationController = LayoutAnimationController(animation)
            layoutAnimationController.delay = 0.5f
            layoutAnimationController.order = LayoutAnimationController.ORDER_NORMAL
            listView.layoutAnimation = layoutAnimationController
        }
    }
}
package com.e.animations

import android.animation.Animator
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.LinearLayout
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

        fun resetLinearLayoutWidthWithWrapper(
            target: LinearLayoutSizeWrapper,
            width: Int,
            listener: OnResetFinishListener
        ) {
            val objectAnimator = ObjectAnimator.ofInt(target, "width", width).setDuration(500)
            objectAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    listener.onRestFinish()
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {}

            })
            objectAnimator.setEvaluator(IntEvaluator())
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator.start()
        }

        fun resetLinearLayoutWidthWithValue(
            target: LinearLayout,
            width: Int,
            listener: OnResetFinishListener
        ) {
            val valueAnimator = ValueAnimator.ofInt(width)
            valueAnimator.addUpdateListener {
                target.layoutParams.width = it.animatedValue as Int
                target.requestLayout()
            }
            valueAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    listener.onRestFinish()
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {}

            })
            valueAnimator.setEvaluator(IntEvaluator())
            valueAnimator.interpolator = AccelerateDecelerateInterpolator()
            valueAnimator.start()
        }
    }

    interface OnResetFinishListener {
        fun onRestFinish()
    }
}
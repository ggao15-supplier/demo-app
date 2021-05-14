package com.ggg.renderscript

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ggg.renderscript.fragments.CustomRSFragment
import com.ggg.renderscript.fragments.DynamicGaussianBlurFragment
import com.ggg.renderscript.fragments.GaussianBlurFragment


/**
 * Created by  gggao on 5/14/2021.
 */
class RenderScriptAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles: Array<String> =
        arrayOf("Gaussian Blur", "Custom RS", "Dynamic Gaussian Blur")
    private val fragments: Array<Fragment> =
        arrayOf(GaussianBlurFragment(), CustomRSFragment(), DynamicGaussianBlurFragment())

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
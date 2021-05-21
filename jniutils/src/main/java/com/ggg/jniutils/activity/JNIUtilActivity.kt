package com.ggg.jniutils.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ggg.jniutils.databinding.ActivityJniUtilsBinding
import com.ggg.jniutils.jni.JNIUtils


/**
 * Created by  gggao on 5/21/2021.
 */
class JNIUtilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJniUtilsBinding = ActivityJniUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCall.setOnClickListener {
            binding.tvShow.text = JNIUtils().callMD5("aaa")
        }
    }
}
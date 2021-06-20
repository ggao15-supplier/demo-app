package com.ggg.jniutils.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ggg.jniutils.databinding.ActivityJniUtilsBinding
import com.ggg.jniutils.jni.JNIUtils


/**
 * Created by  gggao on 5/21/2021.
 */
class JNIUtilActivity : AppCompatActivity() {
    private val jniUtils = JNIUtils()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJniUtilsBinding = ActivityJniUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        jniUtils.listener = object : JNIUtils.OnNativeListener {
            override fun onNative(result: String) {
                binding.tvCreateThread.post { binding.tvCreateThread.text = result }

            }

        }
        binding.btnCall.setOnClickListener {
            binding.tvShow.text = "${jniUtils.callMD5("aaa")}-- ${jniUtils.testLocalFiled}"

        }
        binding.btnArray.setOnClickListener {
            binding.tvArray.text = jniUtils.parseArray(arrayOf("tttt,", "dddd,"))
        }
        binding.btnTypeArray.setOnClickListener {
            binding.tvTypeArray.text = jniUtils.parseTypeArray(intArrayOf(1, 2, 3))
        }
        binding.btnCreateThread.setOnClickListener {
            jniUtils.handlerImageData(byteArrayOf(1,2,3))
        }
    }
}
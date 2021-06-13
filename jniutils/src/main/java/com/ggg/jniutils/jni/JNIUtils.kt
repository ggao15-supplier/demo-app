package com.ggg.jniutils.jni

import android.util.Log

/**
 * Created by  gggao on 5/21/2021.
 * cd /build/tmp/kotlin-classes/debug
 * javah -jni com.ggg.jniutils.jni.JNIUtils
 */
class JNIUtils {
    init {
        System.loadLibrary("native-lib")
    }

    companion object {
        const val testFiled = "test"
    }

    var testLocalFiled: String = "1"
    external fun callMD5(value: String): String
    external fun parseArray(array: Array<String>): String
    external fun parseTypeArray(array: IntArray): String
    external fun handlerImageData(array: ByteArray): String
    fun getTypeValue(type: Int): String {
        return when (type) {
            1 -> "1 value is 1"
            2 -> "2 value is 2"
            3 -> "3 value is 3"
            else -> "empty"
        }
    }

    fun callInThread(arg: String): String {

        Log.d("xxxx", "call in thread :$arg")
        return "Java method"
    }
}
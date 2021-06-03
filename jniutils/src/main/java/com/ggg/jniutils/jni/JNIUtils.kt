package com.ggg.jniutils.jni

/**
 * Created by  gggao on 5/21/2021.
 * cd /build/tmp/kotlin-classes/debug
 * javah -jni com.ggg.jniutils.jni.JNIUtils
 */
class JNIUtils {
    init {
        System.loadLibrary("native-lib")
    }

    external fun callMD5(value: String): String
    external fun parseArray(array: Array<String>): String
    external fun parseTypeArray(array: IntArray): String

    fun getTypeValue(type: Int): String {
        return when (type) {
            1 -> "1 value is 1"
            2 -> "1 value is 1"
            3 -> "1 value is 1"
            else -> "empty"
        }
    }
}
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

    companion object {
        const val testFiled = "test"
    }

    external fun callMD5(value: String): String
    external fun parseArray(array: Array<String>): String
    external fun parseTypeArray(array: IntArray): String

    fun getTypeValue(type: Int): String {
        return when (type) {
            1 -> "1 value is 1"
            2 -> "2 value is 2"
            3 -> "3 value is 3"
            else -> "empty"
        }
    }
}
package com.ggg.jniutils.jni

/**
 * Created by  gggao on 5/21/2021.
 */
class JNIUtils {
   init {
       System.loadLibrary("native-lib")
   }
    external fun callMD5(value: String): String
}
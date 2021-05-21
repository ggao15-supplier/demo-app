//
// Created by gggao on 5/19/2021.
//

#include <jni.h>
#include <string>

extern "C" jstring JNICALL Java_com_ggg_jniutils_jni_JNIUtils_callMD5
  (JNIEnv *env, jobject obj, jstring data){

return env->NewStringUTF("hello world");
}
//
// Created by gggao on 5/19/2021.
//https://juejin.cn/post/6844904192780271630
//

#include <jni.h>
#include <string>
#include <cstdlib>

extern "C" JNIEXPORT jstring JNICALL Java_com_ggg_jniutils_jni_JNIUtils_callMD5
        (JNIEnv *env, jobject obj, jstring data) {
    const char *c_str = env->GetStringUTFChars(data, nullptr);
    jclass jclass1 = env->GetObjectClass(obj);
    return env->NewStringUTF(c_str);
}
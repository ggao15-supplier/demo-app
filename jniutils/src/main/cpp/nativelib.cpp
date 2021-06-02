//
// Created by gggao on 5/19/2021.
//https://juejin.cn/post/6844904192780271630
//

#include <jni.h>
#include <string>
#include <cstdlib>
#include <android/log.h>

#define  LOGE(...) __android_log_print(ANDROID_LOG_ERROR,"JNI",__VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL Java_com_ggg_jniutils_jni_JNIUtils_callMD5
        (JNIEnv *env, jobject obj, jstring data) {
    const char *c_str = env->GetStringUTFChars(data, nullptr);
    jclass clazz = env->GetObjectClass(obj);
    jmethodID method_getTypeValue = env->GetMethodID(clazz, "getTypeValue",
                                                     "(I)Ljava/lang/String;");
    auto values = reinterpret_cast<jstring>( env->CallObjectMethod(obj, method_getTypeValue,
                                                                   1));
    LOGE("value is %s", env->GetStringUTFChars(values, nullptr));
    return env->NewStringUTF(c_str);
}
//
// Created by gggao on 5/19/2021.
//https://juejin.cn/post/6844904192780271630
//

#include <jni.h>
#include <string>
#include <cstdlib>
#include <android/log.h>

#define  LOGE(...) __android_log_print(ANDROID_LOG_INFO,"JNI",__VA_ARGS__)

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

extern "C" JNIEXPORT jstring JNICALL Java_com_ggg_jniutils_jni_JNIUtils_parseArray
        (JNIEnv *env, jobject obj, jobjectArray data) {

    int size = env->GetArrayLength(data);
    char *result = nullptr;

    for (jsize i = 0; i < size; i++) {
        jobject itemObj = env->GetObjectArrayElement(data, i);
        const char *item = env->GetStringUTFChars(
                reinterpret_cast<jstring>(itemObj), nullptr);
        if (result == nullptr) {
            result = (char *) malloc(sizeof(item));
            strcpy(result, item);
        } else {
            result = strcat(result, item);
        }
        env->DeleteLocalRef(itemObj);
    }

    return env->NewStringUTF(result);
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_ggg_jniutils_jni_JNIUtils_parseTypeArray(JNIEnv *env, jobject thiz, jintArray array) {
    int size = env->GetArrayLength(array);
    char *result = (char *) malloc(sizeof(jint));
    jint *firstItem = env->GetIntArrayElements(array, nullptr);
    jclass clazz = env->GetObjectClass(thiz);
    jmethodID methodID = env->GetMethodID(clazz, "getTypeValue", "(I)Ljava/lang/String;");
    auto value = reinterpret_cast<jstring>( env->CallObjectMethod(thiz, methodID, *firstItem));
    strcpy(result, env->GetStringUTFChars(value, nullptr));
    strcat(result, "\n");
    for (int i = 1; i < size; ++i) {
        auto data = reinterpret_cast<jstring>( env->CallObjectMethod(thiz, methodID,
                                                                     *(firstItem + i)));
        strcat(result, env->GetStringUTFChars(data, nullptr));
        if (i < size - 1) {
            strcat(result, "\n");
        }
    }
    env->ReleaseIntArrayElements(array, firstItem, 0);
    env->DeleteLocalRef(clazz);
    return env->NewStringUTF(result);
}
//
// Created by gggao on 6/17/2021.
//

#include "thread_handler.h"


#include <string>
#include <cstdlib>
#include <android/log.h>
#include <thread>

JavaVM *javaVm;
jobject gObj;

char *jbyteArray2Char(JNIEnv *env, jbyteArray data) {
    jbyte *element = env->GetByteArrayElements(data, nullptr);
    jsize length = env->GetArrayLength(data);
    char *result = new char[length + 1];
    memset(result, 0, length + 1);
    memcpy(result, element, length);
    result[length] = 0;
    env->ReleaseByteArrayElements(data, element, 0);
    return result;
}

void *handler(void *threadArg) {
    JNIEnv *env;
    javaVm->AttachCurrentThread(&env, nullptr);
    LOGE("thread handler");
    jclass clazz = env->GetObjectClass(gObj);
    char *element = (char *) threadArg;

    jmethodID method = env->GetMethodID(clazz, "callInThread",
                                        "(Ljava/lang/String;)Ljava/lang/String;");


    int i = 0;
    while (true) {
        char e = *(element + i);
        if (e == 0) {
            break;
        } else {
            LOGE("buffer is %c", e);
            i++;
        }
    }
    env->CallObjectMethod(gObj, method, env->NewStringUTF(element));

    env->DeleteLocalRef(clazz);
    env->DeleteGlobalRef(gObj);

    javaVm->DetachCurrentThread();
    return nullptr;
}

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_ggg_jniutils_jni_JNIUtils_handlerImageData(JNIEnv *env, jobject thiz, jbyteArray array) {
    jstring result = env->NewStringUTF("");
    pthread_t ptr;
    gObj = env->NewGlobalRef(thiz);

    int flag = pthread_create(&ptr, nullptr, handler, jbyteArray2Char(env, array));
    if (flag == 0) {
        LOGE("create success");
    } else {
        LOGE("create failed %d", flag);
    }
    return result;
}

JNIEXPORT jint
JNI_OnLoad(JavaVM
           *jm,
           void *reserved
) {
    javaVm = jm;
    return
            JNI_VERSION_1_6;
}

void parseString(JNIEnv *env, jstring data) {

    const char *c_str = env->GetStringUTFChars(data, nullptr);
    if (c_str == nullptr) {
        return;
    }
    jint size = env->GetStringLength(data);

    for (int i = 0; i < size; ++i) {
        LOGE("c_st is %c ,", *(c_str + 1));
    }
    env->ReleaseStringUTFChars(data, c_str);
}

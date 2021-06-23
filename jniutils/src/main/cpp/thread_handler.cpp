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

struct ImageData {
    jchar *data;
    jsize length;
};

char *jbyteArray2Char(JNIEnv *env, jbyteArray data) {
    jbyte *element = env->GetByteArrayElements(data, JNI_FALSE);
    jsize length = env->GetArrayLength(data);
    char *result = new char[length + 1];
    memset(result, 0, length + 1);
    memcpy(result, element, length);
    result[length] = 0;
    LOGE("result is %s", element);

    env->ReleaseByteArrayElements(data, element, 0);
    return result;
}

void *handler(void *threadArg) {
    JNIEnv *env;
    javaVm->AttachCurrentThread(&env, nullptr);
    LOGE("thread handler");
    jclass clazz = env->GetObjectClass(gObj);
    auto *data = (ImageData *) threadArg;

    jmethodID method = env->GetMethodID(clazz, "callInThread",
                                        "(Ljava/lang/String;)Ljava/lang/String;");


    env->CallObjectMethod(gObj, method, env->NewString(data->data, data->length));

    env->DeleteLocalRef(clazz);
    env->DeleteGlobalRef(gObj);
    free(data);
    javaVm->DetachCurrentThread();
    return nullptr;
}

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_ggg_jniutils_jni_JNIUtils_handlerImageData(JNIEnv *env, jobject thiz, jcharArray array) {
    jstring result = env->NewStringUTF("");
    pthread_t ptr;
    gObj = env->NewGlobalRef(thiz);
    jchar *element = env->GetCharArrayElements(array, JNI_FALSE);
    auto *data = static_cast<ImageData *>(malloc(sizeof(ImageData)));
    data->data = element;
    data->length = env->GetArrayLength(array);
    int flag = pthread_create(&ptr, nullptr, handler, data);
    if (flag == 0) {
        LOGE("create success");
    } else {
        LOGE("create failed %d", flag);
    }
    env->ReleaseCharArrayElements(array, element, 0);

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

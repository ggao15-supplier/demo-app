//
// Created by gggao on 6/17/2021.
//

#ifndef DEMO_APP_THREAD_HANDLER_H
#define DEMO_APP_THREAD_HANDLER_H
#include <jni.h>
#define  LOGE(...) __android_log_print(ANDROID_LOG_INFO,"JNI",__VA_ARGS__)
void parseString(JNIEnv *env, jstring data);
#endif //DEMO_APP_THREAD_HANDLER_H

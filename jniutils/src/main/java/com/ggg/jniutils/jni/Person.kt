package com.ggg.jniutils.jni


/**
 * Created by  gggao on 6/10/2021.
 */
data class Person(val name: String, val age: Int) {

    fun print(): String {
        return "name:$name;age:$age"
    }
}
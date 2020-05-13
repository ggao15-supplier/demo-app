package com.ggg.myapplication.views.home

import kotlin.coroutines.coroutineContext

data class Point(val x: Double, val y: Double)

operator fun Point.plus(p: Point): Point {
    return Point(x + p.x, y + p.y)
}

inline fun a(): Boolean {
    val a = (0..10).any {
        print("it is $it")
        return it == 9
    }
    "aa".padStart(2, '0')
    "aa".padEnd(2, '4')
    print("a is $a")
    with(a) {

    }
    a.run {

    }
    a.also {

    }
    a.apply {

    }

    (1..10).map { }
    (1..10).average()

    return a
}

@Retention(value = AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class CC(val a: String) {

}

internal class sss {
    companion object {
        @JvmStatic
        val a = 1
        @JvmField
        val e=3

         val b = 1
    }

    @JvmField
    val c = 1
    val d = 3
    lateinit var testObject: String
    fun test() {
        if (::testObject.isLateinit) {
            //是否已经初始化
        }
    }

    fun aa() {
        val (x, y) = com.ggg.myapplication.views.home.Point(1.0, 2.0)

        print(x)
    }

}


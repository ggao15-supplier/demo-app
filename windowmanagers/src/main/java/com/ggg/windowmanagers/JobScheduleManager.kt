package com.ggg.windowmanagers

import android.util.Log
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * 任务调度执行模块
 */
class JobScheduleManager {
    companion object {
        const val TAG = "JobScheduleManager"
    }

    fun run() {
        val net1 = WorkRunnable(WorkRunnable.TYPE_NET, 10)
        val net2 = WorkRunnable(WorkRunnable.TYPE_NET, 50)
        val net3 = WorkRunnable(WorkRunnable.TYPE_NET)

        val file1 = WorkRunnable(WorkRunnable.TYPE_FILE, 20)
        val file2 = WorkRunnable(WorkRunnable.TYPE_FILE)
        val file3 = WorkRunnable(WorkRunnable.TYPE_FILE, 40)

        val other1 = WorkRunnable(WorkRunnable.TYPE_OTHER, 21)
        val other2 = WorkRunnable(WorkRunnable.TYPE_OTHER)
        val other3 = WorkRunnable(WorkRunnable.TYPE_OTHER, 13)

        poolExecutor.submit(net1)
        poolExecutor.submit(net2)
        poolExecutor.submit(net3)
        poolExecutor.submit(file1)
        poolExecutor.submit(file2)
        poolExecutor.submit(file3)
        poolExecutor.submit(other1)
        poolExecutor.submit(other2)
        poolExecutor.submit(other3)
    }

    //使用 优先级的block 队列
    private val queue: BlockingQueue<Runnable> =
        PriorityBlockingQueue(10, Comparator<Runnable> { o1, o2 ->
            val baseRunnable1 = o1 as WorkRunnable
            val baseRunnable2 = o2 as WorkRunnable
            baseRunnable1.priority - baseRunnable2.priority
        })

    private val poolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        10,
        20,
        10L,
        TimeUnit.SECONDS, queue
    )

    class WorkRunnable(private val type: Int, val priority: Int = 1) : Runnable {
        companion object {
            const val TYPE_NET = 1
            const val TYPE_FILE = 2
            const val TYPE_OTHER = 3
        }

        override fun run() {
            when (type) {
                TYPE_NET -> network()
                TYPE_FILE -> file()
                TYPE_OTHER -> other()
            }
        }

        private fun network() {
            Log.d(TAG, "network:${hashCode()} is start ;priority is :$priority")
            Thread.sleep(3000)
            Log.d(TAG, "network:${hashCode()} is end ;priority is :$priority")
        }

        private fun file() {
            Log.d(TAG, "network:${hashCode()} is start ;priority is :$priority")
            Thread.sleep(3000)
            Log.d(TAG, "network:${hashCode()} is end ;priority is :$priority")
        }

        private fun other() {
            Log.d(TAG, "network:${hashCode()} is start ;priority is :$priority")
            Thread.sleep(3000)
            Log.d(TAG, "network:${hashCode()} is end ;priority is :$priority")
        }
    }

}
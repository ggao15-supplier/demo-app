package com.fprdmps.mobileappcn.windowmanagers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import android.widget.ImageView
import java.io.File
import java.lang.ref.SoftReference

/**
 * 图片管理模块
 */
class ImageLoadManager(private val imageView: ImageView) {
    private val memoryCache: LruCache<String, Bitmap?> = LruCache(10)
    private val diskCache: LruCache<String, String?> = LruCache(20)
    fun load(url: String) {
        val bitmap: Bitmap? = memoryCache[url]
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
        } else {
            val path: String? = diskCache[url]
            val file = SoftReference<File>(File(path))

            if ((path != null && file.get() != null && file.get()!!.exists())) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(path))
            } else {
                downLoad(url)
            }
            file.clear()
        }
    }

    private fun downLoad(url: String) {

        Thread(Runnable {
            //在子线程中异步下载图片
            val path = ""//图片下载路径
            val bitmap = addFile(url, path)
            imageView.post {
                imageView.setImageBitmap(bitmap)
            }
        }).start()

    }

    private fun addFile(url: String, path: String): Bitmap {
        val bitmap = BitmapFactory.decodeFile(path)
        val preBitmap = memoryCache.put(url, bitmap)
        preBitmap?.recycle()

        val prePath = diskCache.put(url, path)
        val file = SoftReference<File>(File(prePath))
        if (file.get() != null && file.get()!!.exists()) {
            file.get()!!.delete()
        }
        file.clear()
        return bitmap
    }
}
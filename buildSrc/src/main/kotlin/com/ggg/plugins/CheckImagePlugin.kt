package com.ggg.plugins

import com.android.build.gradle.BaseExtension
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.FileInputStream


/**
 * Created by  gggao on 7/6/2021.
 */
class CheckImagePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("checkImageResources") {
            group = "aa"
            doLast {
                val baseExtension = target.extensions.getByName("android") as BaseExtension
                baseExtension.sourceSets.flatMap {
                    it.res.srcDirs + it.assets.srcDirs
                }.flatMap {
                    it.walk().filter { it.isFile }.toList()
                }.map {
                    FileInfo(
                        it.name,
                        it.length(),
                        it.absolutePath,
                        DigestUtils.md5Hex(FileInputStream(it))
                    )
                }.groupBy {
                    it.md5
                }.filter {
                    it.value.size > 1
                }.onEach {
                    println("md5:" + it.key + " size: " + it.value[0].size)
                }.also {
                    if (it.isNotEmpty()) {
                        throw RuntimeException("检测到重复的资源")
                    }
                }
            }
        }
    }

    data class FileInfo(val name: String, val size: Long, val path: String, val md5: String)
}

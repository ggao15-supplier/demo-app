package com.ggg.myapplication.views.home

import com.ggg.myapplication.selfopen.SelfModule

data class MusicItem(
    val songName: String,
    val singer: String,
    val alumPhoto: String?=null,
    val module: SelfModule? = null
)
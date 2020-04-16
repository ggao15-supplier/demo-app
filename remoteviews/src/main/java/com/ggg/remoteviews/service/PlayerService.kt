package com.ggg.remoteviews.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.ggg.remoteviews.appwidget.PlayerAppWidgetProvider
import com.ggg.remoteviews.notification.PlayerNotification

class PlayerService : Service() {
    private var isPlay = false
    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("xxx", "action:${intent?.getStringExtra(PlayerAppWidgetProvider.EXTRA_CLICK_ACTION)}")
        intent?.let {
            var photoPath = ""
            var songName = ""
            var singer = ""
            var playAction = it.getStringExtra(PlayerAppWidgetProvider.EXTRA_CLICK_ACTION)
            val playerNotification = PlayerNotification(this)

            when (playAction) {
                PlayerAppWidgetProvider.CLICK_PRE -> {
                    songName = "god is a girl"
                    singer = "Crystal"
                }
                PlayerAppWidgetProvider.CLICK_PAUSE_PLAY -> {
                    isPlay = !isPlay
                    if (isPlay) {
                        songName = "god is a girl"
                        singer = "Crystal"
                        playAction = PlayerAppWidgetProvider.CLICK_PLAY
                    } else {
                        playAction = PlayerAppWidgetProvider.CLICK_PAUSE
                    }
                }
                PlayerAppWidgetProvider.CLICK_NEXT -> {
                    songName = "god is a boy"
                    singer = "Alex"
                }
            }
            //使用前台notification 用于保活
            startForeground(
                PlayerNotification.notificationId,
                playerNotification.createNotification(photoPath, songName, singer, isPlay)
            )
            sendBroadcast(
                Intent(playAction).setComponent(
                    ComponentName(
                        this,
                        PlayerAppWidgetProvider::class.java
                    )
                )
                    .putExtra(PlayerAppWidgetProvider.EXTRA_SONG, songName)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_PHOTO, photoPath)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_SINGER, singer)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_STATE, isPlay)
            )

        }
        return START_STICKY
    }
}
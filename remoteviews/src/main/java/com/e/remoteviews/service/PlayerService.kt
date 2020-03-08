package com.e.remoteviews.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.e.remoteviews.appwidget.PlayerAppWidgetProvider

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

            sendBroadcast(
                Intent(playAction)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_SONG, songName)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_PHOTO, photoPath)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_SINGER, singer)
                    .putExtra(PlayerAppWidgetProvider.EXTRA_STATE, isPlay)
            )

        }
        return super.onStartCommand(intent, flags, startId)
    }
}
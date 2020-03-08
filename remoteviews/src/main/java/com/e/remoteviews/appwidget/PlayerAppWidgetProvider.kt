package com.e.remoteviews.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.util.Log
import android.widget.RemoteViews
import com.e.remoteviews.R
import com.e.remoteviews.service.PlayerService

class PlayerAppWidgetProvider : AppWidgetProvider() {
    companion object {
        const val CLICK_PRE = "com.e.appwiget.player.pre"
        const val CLICK_NEXT = "com.e.appwiget.player.next"
        const val CLICK_PLAY = "com.e.appwiget.player.play"
        const val CLICK_PAUSE = "com.e.appwiget.player.pause"
        const val CLICK_PAUSE_PLAY = "com.e.onclick_pause_play"
        const val EXTRA_CLICK_ACTION = "com.e.onclick_action"
        const val EXTRA_PHOTO = "extra_photo_path"
        const val EXTRA_SINGER = "extra_singer"
        const val EXTRA_STATE = "extra_state"
        const val EXTRA_SONG = "extra_song"
    }

    private val actions = arrayOf(CLICK_NEXT, CLICK_PRE, CLICK_PAUSE, CLICK_PLAY)
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("xxx", "onUpdate")

        updateAppWidgets(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.d("xxx", "provider::${intent?.action}")
        context?.let { cxt ->
            if (intent?.action in actions) {
                val remoteViews = RemoteViews(cxt.packageName, R.layout.widget_player)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                var isPlay = false
                when (intent?.action) {
                    CLICK_PAUSE -> isPlay = false
                    CLICK_PLAY -> isPlay = true
                }
                updateView(
                    remoteViews,
                    intent?.getStringExtra(EXTRA_PHOTO) ?: "",
                    intent?.getStringExtra(EXTRA_SONG) ?: "",
                    intent?.getStringExtra(EXTRA_SINGER) ?: "",
                    isPlay
                )
                appWidgetManager.updateAppWidget(ComponentName(cxt, AppWidgetProvider::class.java), remoteViews)
            }
        }

    }

    private fun updateView(
        remoteViews: RemoteViews,
        photoPath: String,
        songName: String,
        singer: String,
        isPlay: Boolean
    ) {
        if (!TextUtils.isEmpty(photoPath)) {
            val bitmap = BitmapFactory.decodeFile(photoPath)
            remoteViews.setImageViewBitmap(R.id.iv_photo, bitmap)
        }
        remoteViews.setTextViewText(R.id.tv_singer, singer)
        remoteViews.setTextViewText(R.id.tv_song_name, songName)
        remoteViews.setImageViewResource(R.id.iv_play, if (isPlay) R.drawable.ic_pause else R.drawable.ic_play)
    }

    private fun updateClick(context: Context?, remoteViews: RemoteViews) {
        remoteViews.setOnClickPendingIntent(
            R.id.iv_pre,
            PendingIntent.getService(
                context,
                100,
                Intent(context, PlayerService::class.java).putExtra(EXTRA_CLICK_ACTION, CLICK_PRE),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews.setOnClickPendingIntent(
            R.id.iv_play,
            PendingIntent.getService(
                context,
                200,
                Intent(context, PlayerService::class.java).putExtra(EXTRA_CLICK_ACTION, CLICK_PAUSE_PLAY),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews.setOnClickPendingIntent(
            R.id.iv_next,
            PendingIntent.getService(
                context,
                300,
                Intent(context, PlayerService::class.java).putExtra(EXTRA_CLICK_ACTION, CLICK_NEXT),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

    private fun updateAppWidgets(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        appWidgetIds?.let { array ->
            appWidgetManager?.let { manager ->
                array.forEach {
                    val remoteViews = RemoteViews(context?.packageName, R.layout.widget_player)
                    updateView(remoteViews, "", "", "", false)
                    updateClick(context, remoteViews)
                    manager.updateAppWidget(it, remoteViews)
                }

            }
        }
    }
}
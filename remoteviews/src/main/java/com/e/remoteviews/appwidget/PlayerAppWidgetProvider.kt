package com.e.remoteviews.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.RemoteViews
import com.e.remoteviews.R
import com.e.remoteviews.service.PlayerService

class PlayerAppWidgetProvider : AppWidgetProvider() {
    companion object {
        const val CLICK_PRE = "com.e.onclick_pre"
        const val CLICK_NEXT = "com.e.onclick_next"
        const val CLICK_PLAY = "com.e.onclick_play"
        const val CLICK_PAUSE = "com.e.onclick_pause"
        const val CLICK_PAUSE_PLAY = "com.e.onclick_pause_play"
        const val CLICK_ACTION = "com.e.onclick_action"
        const val EXTRA_PHOTO = "extra_photo_path"
        const val EXTRA_SINGER = "extra_singer"
        const val EXTRA_SONG = "extra_song"
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("xxx", "onUpdate")

        updateAppWidgets(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        context?.let { cxt ->
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
        remoteViews.setBoolean(R.id.iv_play, "setSelected", isPlay)
    }

    private fun updateClick(context: Context?, remoteViews: RemoteViews) {
        remoteViews.setOnClickPendingIntent(
            R.id.iv_pre,
            PendingIntent.getService(
                context,
                0,
                Intent(context, PlayerService::class.java).putExtra(CLICK_ACTION, CLICK_PRE),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews.setOnClickPendingIntent(
            R.id.iv_play,
            PendingIntent.getService(
                context,
                0,
                Intent(context, PlayerService::class.java).putExtra(CLICK_ACTION, CLICK_PAUSE_PLAY),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews.setOnClickPendingIntent(
            R.id.iv_next,
            PendingIntent.getService(
                context,
                0,
                Intent(context, PlayerService::class.java).putExtra(CLICK_ACTION, CLICK_NEXT),
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
package com.e.remoteviews.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.text.TextUtils
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import com.e.remoteviews.R
import com.e.remoteviews.appwidget.PlayerAppWidgetProvider
import com.e.remoteviews.service.PlayerService

class PlayerNotification(private val context: Context) {
    companion object {
        private const val channelId = "player"
        const val notificationId = 1
    }

    private var remoteViews: RemoteViews? = null
    fun createNotification(
        photoPath: String,
        songName: String,
        singer: String,
        isPlay: Boolean
    ): Notification {
        val mBuilder = NotificationCompat.Builder(context, channelId)
        remoteViews = RemoteViews(context.packageName, R.layout.widget_player)
        updateView(photoPath, songName, singer, isPlay)
        updateClick(context)
        mBuilder.setCustomContentView(remoteViews)
        mBuilder.setSmallIcon(R.drawable.ic_photo_album_black_24dp)
        mBuilder.priority = PRIORITY_MAX
        mBuilder.setOngoing(true)
        mBuilder.setAutoCancel(true)
        mBuilder.setShowWhen(false)
        mBuilder.setWhen(System.currentTimeMillis())
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_LOW
                )
            )
        }
//        notificationManager.notify(notificationId, mBuilder.build())
        return mBuilder.build()
    }

    private fun updateView(
        photoPath: String,
        songName: String,
        singer: String,
        isPlay: Boolean
    ) {
        if (!TextUtils.isEmpty(photoPath)) {
            val bitmap = BitmapFactory.decodeFile(photoPath)
            remoteViews?.setImageViewBitmap(R.id.iv_photo, bitmap)
        }
        remoteViews?.setTextViewText(R.id.tv_singer, singer)
        remoteViews?.setTextViewText(R.id.tv_song_name, songName)
        remoteViews?.setImageViewResource(
            R.id.iv_play,
            if (isPlay) R.drawable.ic_pause else R.drawable.ic_play
        )
    }

    private fun updateClick(context: Context?) {
        remoteViews?.setOnClickPendingIntent(
            R.id.iv_pre,
            PendingIntent.getService(
                context,
                100,
                Intent(context, PlayerService::class.java).putExtra(
                    PlayerAppWidgetProvider.EXTRA_CLICK_ACTION,
                    PlayerAppWidgetProvider.CLICK_PRE
                ),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews?.setOnClickPendingIntent(
            R.id.iv_play,
            PendingIntent.getService(
                context,
                200,
                Intent(context, PlayerService::class.java).putExtra(
                    PlayerAppWidgetProvider.EXTRA_CLICK_ACTION,
                    PlayerAppWidgetProvider.CLICK_PAUSE_PLAY
                ),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        remoteViews?.setOnClickPendingIntent(
            R.id.iv_next,
            PendingIntent.getService(
                context,
                300,
                Intent(context, PlayerService::class.java).putExtra(
                    PlayerAppWidgetProvider.EXTRA_CLICK_ACTION,
                    PlayerAppWidgetProvider.CLICK_NEXT
                ),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }
}
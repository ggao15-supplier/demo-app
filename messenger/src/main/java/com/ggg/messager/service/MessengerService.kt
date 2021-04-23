package com.ggg.messager.service

import android.app.Service
import android.content.Intent
import android.os.*


/**
 * Created by  gggao on 4/23/2021.
 */
class MessengerService : Service() {
    private val messenger: Messenger = Messenger(Handler(
        mainLooper
    ) {
        it.replyTo?.apply {
            this.send(Message.obtain().also { newMsg ->
                newMsg.data = Bundle().also { bundle ->
                    bundle.putString("data", "${MessengerService::class.simpleName}")

                }
            })
        }
        return@Handler true
    })

    override fun onBind(intent: Intent?): IBinder? {
        return messenger.binder
    }
}
package com.ggg.messager.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.ggg.messager.R
import com.ggg.messager.databinding.ActivityMessengerClientBinding

class MessengerClientActivity : AppCompatActivity() {
    private var binding: ActivityMessengerClientBinding? = null
    private val client: Messenger = Messenger(Handler {
        binding?.tvShow?.text = "${binding?.tvShow?.text} \n ${it.data.getString("data")}"
        true
    })
    private var serviceMessenger: Messenger? = null
    private var connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceMessenger = Messenger(service)

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceMessenger = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessengerClientBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        bindService(
            Intent("com.ggg.messager.MessengerService").setPackage(packageName), connection,
            BIND_AUTO_CREATE
        )
        binding?.apply {
            btnAdd.setOnClickListener {
                val message = Message.obtain()
                message.data = Bundle().also {
                    it.putString("data", "come from client:${Math.random()}")
                }
                message.replyTo = client

                serviceMessenger?.send(message)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}
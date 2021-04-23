package com.ggg.messager.client

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.ggg.messager.R
import com.ggg.messager.databinding.ActivityMessengerClientBinding

class MessengerClientActivity : AppCompatActivity() {
    private var binding: ActivityMessengerClientBinding? = null
    private val client: Messenger = Messenger(Handler(
        mainLooper
    ) {

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

        binding?.apply {
            btnAdd.setOnClickListener {

            }
        }

    }
}
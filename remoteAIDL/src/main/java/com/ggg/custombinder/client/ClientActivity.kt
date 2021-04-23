package com.ggg.custombinder.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.ggg.custombinder.IUserEntity
import com.ggg.custombinder.bean.UserBean
import com.ggg.custombinder.databinding.ActivityClientBinding


/**
 * Created by  gggao on 4/23/2021.
 */
class ClientActivity : AppCompatActivity() {
    private var userEntity: IUserEntity? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            service?.apply {
                userEntity = IUserEntity.Stub.asInterface(this)

            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            userEntity = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityClientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindService(
            Intent().setAction("com.ggg.remote_service")
                .setPackage(packageName),
            serviceConnection,
            BIND_AUTO_CREATE
        )


        binding.btnAdd.setOnClickListener {
            userEntity?.apply {
                addUser(UserBean("aaa${Math.random()}"))
            }
        }
        binding.btnShow.setOnClickListener {
            userEntity?.apply {
                binding.tvShow.text = ""
                this.users.forEach { bean ->
                    binding.tvShow.text = "${binding.tvShow.text}\n${bean.name}"
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}
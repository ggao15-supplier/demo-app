package com.ggg.custombinder.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.ggg.custombinder.IUserEntity
import com.ggg.custombinder.bean.UserBean


/**
 * Created by  gggao on 4/23/2021.
 */
class RemoteService : Service() {
    private val userList = mutableListOf<UserBean>()
    private val binder: IBinder = object : IUserEntity.Stub() {
        override fun addUser(entity: UserBean?) {
            entity?.apply { userList.add(this) }
        }

        override fun getUsers(): MutableList<UserBean> {
            return userList
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
package com.ggg.custombinder.bean

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by  gggao on 4/22/2021.
 */
data class UserBean(val name: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun describeContents(): Int = 0


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<UserBean> {
        override fun createFromParcel(parcel: Parcel): UserBean {
            return UserBean(parcel)
        }

        override fun newArray(size: Int): Array<UserBean?> {
            return arrayOfNulls(size)
        }
    }

}

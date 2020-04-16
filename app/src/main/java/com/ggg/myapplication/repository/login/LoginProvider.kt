package com.ggg.myapplication.repository.login

import com.ggg.myapplication.repository.login.api.LoginApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginProvider {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("").build()

    }

    fun login(
        userName: String,
        phone: String,
        password: String
    ) {
        retrofit.create(LoginApiService::class.java).login(userName, phone, password)
            .enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                }

            })
    }
}
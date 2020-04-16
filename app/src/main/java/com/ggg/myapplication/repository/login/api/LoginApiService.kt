package com.ggg.myapplication.repository.login.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

interface LoginApiService {

    @FormUrlEncoded
    @PUT("/login")
    fun login(
        @Field("userName") userName: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<ResponseBody>
}
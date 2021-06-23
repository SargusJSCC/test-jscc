package com.kotlinviper.products.webServices

import com.kotlinviper.products.data.loader.LoginResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("login")
    fun login(@Body headers: RequestBody): Call<LoginResponseModel>


}
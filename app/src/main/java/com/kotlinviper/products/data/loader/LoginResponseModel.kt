package com.kotlinviper.products.data.loader

import com.google.gson.annotations.SerializedName

data class LoginResponseModel (
    @SerializedName("status")
    var status: Boolean = false,
    @SerializedName("codeStatus")
    var codeStatus: String ,
    @SerializedName("message")
    var message: String ,
    @SerializedName("data")
    var data: String ,
    @SerializedName("count")
    var count: Int = 0

)
package com.kotlinviper.products.utilities

import android.util.Log

object LogUtil {

    fun e(tag: String, message: String, exception: Throwable) {
        Log.e(tag, message, exception)
    }

}
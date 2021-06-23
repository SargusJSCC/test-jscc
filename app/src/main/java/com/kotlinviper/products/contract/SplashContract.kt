package com.kotlinviper.products.contract

import android.content.Context

interface SplashContract {
    interface View {
        fun finishView()
        fun getContext(): Context
    }

    interface Presenter {
        fun onViewCreated()
        fun onDestroy()
    }
}

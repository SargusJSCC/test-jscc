package com.kotlinviper.products.contract

import android.content.Context

class LoginContracts {

    interface View {
        fun setUserError(resId: Int)
        fun setPasswordError(resId: Int)
        fun showError(message: String)
        fun finishView()
        fun getContext(): Context
        fun showLoading()
        fun hideLoading()
    }

    interface Interactor {
        fun login(username: String, password: String)
    }
    interface InteractorOutput {
        fun onLoginSuccess()
        fun onLoginError(message: String)
        fun onProgressLogin()
        fun offProgressLogin()
    }

    interface Presenter {

        fun onDestroy()
        fun onLoginButtonPressed(username: String, password: String)
    }

}
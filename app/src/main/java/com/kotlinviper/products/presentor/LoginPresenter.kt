package com.kotlinviper.products.presentor

import android.content.Context
import android.text.TextUtils
import com.kotlinviper.products.contract.LoginContracts
import com.kotlinviper.products.R
import com.kotlinviper.products.interactor.LoginInteractor
import com.kotlinviper.products.router.Router


class LoginPresenter(var view: LoginContracts.View?, var router: Router, val context: Context) : LoginContracts.Presenter,
    LoginContracts.InteractorOutput {

    var interactor: LoginContracts.Interactor? = LoginInteractor(this , context)


    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun onLoginButtonPressed(username: String, password: String) {

        if (TextUtils.isEmpty(username)) {
            view?.setUserError(R.string.err_username)
        } else if (TextUtils.isEmpty(password)) {
            view?.setPasswordError(R.string.err_password)
        } else {
            interactor?.login(username, password)
        }
    }

    override fun onLoginSuccess() {
        router.showproductsList(view?.getContext())
        view?.finishView()
    }


    override fun onLoginError(message: String) {
        view?.showError(message)
    }


    override fun onProgressLogin() {
        view?.showLoading()

    }

    override fun offProgressLogin() {
        view?.hideLoading()
    }
}
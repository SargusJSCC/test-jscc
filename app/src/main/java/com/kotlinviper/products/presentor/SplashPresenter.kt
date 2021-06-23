package com.kotlinviper.products.presentor

import com.kotlinviper.products.contract.SplashContract
import com.kotlinviper.products.router.Router


class SplashPresenter(private var view: SplashContract.View?, var router: Router) :
    SplashContract.Presenter {

    override fun onViewCreated() {
        router.showproductsList(view?.getContext())
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }
}
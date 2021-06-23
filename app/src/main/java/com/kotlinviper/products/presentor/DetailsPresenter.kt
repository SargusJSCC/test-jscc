package com.kotlinviper.products.presentor

import android.app.Activity
import com.kotlinviper.products.contract.DetailsContract
import com.kotlinviper.products.entity.Product
import com.kotlinviper.products.interactor.DetailsInteractor
import com.kotlinviper.products.router.Router


class DetailsPresenter(private var view: DetailsContract.View?, var router: Router) :
    DetailsContract.Presenter, DetailsContract.InteractorOutput {

    private var interactor: DetailsContract.Interactor? = DetailsInteractor(this)

    override fun onViewCreated(productId: String?) {
        interactor?.loadproduct(productId)
    }

    override fun backButtonClicked() {
        router.exit(view?.getContext() as Activity)
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun onFavoriteproductSuccess(data: Product) {
        view?.showproductData(data)
    }

    override fun onFavoriteproductError() {
        view?.showInfoMessage("Error when updating product")
    }

    override fun onLoadproductSuccess(data: Product) {
        view?.showproductData(data)
    }

    override fun onLoadproductError() {
        view?.showInfoMessage("Error when loading product")
    }
}

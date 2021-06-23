package com.kotlinviper.products.presentor

import com.kotlinviper.products.contract.ListContract
import com.kotlinviper.products.entity.Header
import com.kotlinviper.products.interactor.ListInteractor
import com.kotlinviper.products.router.Router


class ListPresenter(private var view: ListContract.View?, private var router: Router) :
    ListContract.Presenter,
    ListContract.InteractorOutput {

    private var interactor: ListContract.Interactor? = ListInteractor(this)

    override fun productItemItemClicked(productId: String) {
        router.showproductDetails(view?.getContext(), productId)
    }

    override fun goViewLogin() {
        router.goLogin(view?.getContext())
        view?.finishView()
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadproductsList()
    }

    override fun onLoadproductsListSuccess(data: List<Header>) {
        view?.hideLoading()
        view?.showproducts(data)
    }

    override fun onLoadproductsListError() {
        view?.hideLoading()
        view?.showInfoMessage("Error when loading data")
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}

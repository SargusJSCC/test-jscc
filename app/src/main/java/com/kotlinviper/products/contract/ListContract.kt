package com.kotlinviper.products.contract

import android.content.Context
import com.kotlinviper.products.entity.Header

interface ListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showproducts(data: List<Header>)
        fun showInfoMessage(msg: String)
        fun getContext(): Context
        fun finishView()
    }

    interface Presenter {
        // User actions
        fun productItemItemClicked(productId: String)

        fun goViewLogin()

        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }

    interface Interactor {
        fun loadproductsList()
    }

    interface InteractorOutput {
        fun onLoadproductsListSuccess(data: List<Header>)
        fun onLoadproductsListError()
    }
}

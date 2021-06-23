package com.kotlinviper.products.contract

import android.content.Context
import com.kotlinviper.products.entity.Product

interface DetailsContract {

    interface View {
        fun showInfoMessage(msg: String)
        fun showproductData(product: Product?)
        fun getContext(): Context
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()

        // Model updates
        fun onViewCreated(productId: String?)

        fun onDestroy()
    }

    interface Interactor {
        fun loadproduct(productId: String?)
    }

    interface InteractorOutput {
        fun onFavoriteproductSuccess(data: Product)
        fun onFavoriteproductError()
        fun onLoadproductSuccess(data: Product)
        fun onLoadproductError()
    }
}
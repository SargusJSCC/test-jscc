package com.kotlinviper.products.interactor

import android.annotation.SuppressLint
import com.kotlinviper.products.contract.DetailsContract
import com.kotlinviper.products.entity.Repository
import com.kotlinviper.products.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsInteractor constructor(private var outputContract: DetailsContract.InteractorOutput) :
    DetailsContract.Interactor {

    companion object {
        val TAG: String by lazy { DetailsInteractor::class.java.simpleName }
    }

    init {
        com.kotlinviper.products.DemoApplication.INSTANCE.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository


    @SuppressLint("CheckResult")
    override fun loadproduct(productId: String?) {
        productId?.let {
            repository.getproduct(it).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ product ->
                    outputContract.onLoadproductSuccess(product)
                }, { e ->
                    LogUtil.e(TAG, "Error when getting products", e)
                    outputContract.onLoadproductError()
                })
        }
    }
}



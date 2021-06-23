package com.kotlinviper.products.interactor

import android.annotation.SuppressLint
import com.kotlinviper.products.contract.ListContract
import com.kotlinviper.products.entity.Repository
import com.kotlinviper.products.utilities.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListInteractor constructor(private var outputContract: ListContract.InteractorOutput) :
    ListContract.Interactor {

    companion object {
        val TAG: String by lazy { ListInteractor::class.java.simpleName }
    }

    init {
        com.kotlinviper.products.DemoApplication.INSTANCE.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    @SuppressLint("CheckResult")
    override fun loadproductsList() {
        repository.getproductHeaders()

            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ products ->
                outputContract.onLoadproductsListSuccess(products)
            }, { e ->
                LogUtil.e(TAG, "Error when getting products", e)
                outputContract.onLoadproductsListError()
            })
    }

}



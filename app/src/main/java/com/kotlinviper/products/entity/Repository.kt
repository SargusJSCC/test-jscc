package com.kotlinviper.products.entity

import com.kotlinviper.products.data.AppDatabase
import io.reactivex.Flowable
import io.reactivex.Single

class Repository constructor(private val appDatabase: AppDatabase) : Repo{

    override fun getproductHeaders(): Flowable<List<Header>> {
        return appDatabase.productDao().getproductHeaders()
    }

    override fun getproduct(productId: String): Single<Product> {
        return appDatabase.productDao().getproduct(productId)
    }

    override fun update(product: Product): Single<Int> {
        return appDatabase.productDao().update(product)
    }
}
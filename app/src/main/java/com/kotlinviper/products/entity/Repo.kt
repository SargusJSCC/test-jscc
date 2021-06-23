package com.kotlinviper.products.entity

import io.reactivex.Flowable
import io.reactivex.Single

interface Repo {

    fun getproductHeaders(): Flowable<List<Header>>

    fun getproduct(productId: String): Single<Product>

    fun update(product: Product): Single<Int>
}
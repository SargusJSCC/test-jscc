package com.kotlinviper.products.entity

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getproducts(): Flowable<List<Product>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getproduct(productId: String): Single<Product>

    @Query("SELECT * FROM products")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getproductHeaders(): Flowable<List<Header>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(product: Product): Single<Int>
}

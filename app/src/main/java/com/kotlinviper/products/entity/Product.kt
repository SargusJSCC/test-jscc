package com.kotlinviper.products.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(

    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val path: String,
    val image: String?,
    val title: String?,
    val longDescription: String?,
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String?
)




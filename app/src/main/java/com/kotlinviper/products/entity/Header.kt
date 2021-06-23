package com.kotlinviper.products.entity

import androidx.room.ColumnInfo

data class Header (

    val id: String,
    val path: String?,
    val image: String?,
    val title: String?,
    val longDescription: String?,
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String?

)
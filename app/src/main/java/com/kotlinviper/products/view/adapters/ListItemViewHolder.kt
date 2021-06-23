package com.kotlinviper.products.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kotlinviper.products.R
import com.kotlinviper.products.entity.Header
import kotlinx.android.synthetic.main.activity_products_detail.view.*


class ListItemViewHolder(parent: ViewGroup, val context: Context) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
) {

    var offer: Header? = null

    fun bindTo(offer: Header?) {
        this.offer = offer
        itemView.name.text = offer?.shortDescription
        offer?.image?.let { itemView.imageView.loadUrl(it) }
    }


    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(20)
            .error(R.drawable.baseline_insert_photo_black_48)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}


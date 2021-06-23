package com.kotlinviper.products.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinviper.products.entity.Header


class ListAdapter(private var listener: (String?) -> Unit, private var dataList: List<Header>?) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    override fun getItemCount() = dataList?.size ?: 0


    override fun onBindViewHolder(holderListItem: ListItemViewHolder, position: Int) {
        holderListItem.bindTo(getItem(position))
        holderListItem.itemView.setOnClickListener { listener(dataList?.get(position)?.id) }
    }

    private fun getItem(position: Int): Header? {
        return dataList?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
        ListItemViewHolder(parent, parent.context)

    fun updateData(list: List<Header>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }

}

package com.kotlinviper.products.router

import android.app.Activity
import android.content.Context
import com.kotlinviper.products.view.activities.LoginActivity
import com.kotlinviper.products.view.activities.DetailActivity
import com.kotlinviper.products.view.activities.ListActivity


class Router {

    fun showproductsList(context: Context?) {
        context?.let {
            val intent = ListActivity.callingIntent(context)
            context.startActivity(intent)
        }
    }

    fun showproductDetails(context: Context?, productId: String?) {
        context?.let {
            val intent = DetailActivity.callingIntent(context, productId)
            context.startActivity(intent)
        }
    }

    fun goLogin(context: Context?) {
        context?.let {
            val intent = LoginActivity.callingIntent(context)
            context.startActivity(intent)
        }
    }

    fun exit(activity: Activity?) {
        activity?.finish()
    }
}
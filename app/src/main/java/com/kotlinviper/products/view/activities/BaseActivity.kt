package com.kotlinviper.products.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.kotlinviper.products.di.ApplicationComponent
import com.kotlinviper.products.router.Router
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as com.kotlinviper.products.DemoApplication).appComponent
    }

    override fun onResume() {
        super.onResume()
    }

    @Inject
    lateinit var router: Router

    fun getContext(): Context {
        return this
    }
}



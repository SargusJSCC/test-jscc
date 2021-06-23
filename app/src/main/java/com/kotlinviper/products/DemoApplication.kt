package com.kotlinviper.products

import android.app.Application
import com.kotlinviper.products.di.ApplicationComponent
import com.kotlinviper.products.di.ApplicationModule
import com.kotlinviper.products.di.DaggerApplicationComponent


class DemoApplication : Application() {

    companion object {
        lateinit var INSTANCE: DemoApplication
    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        this.injectMembers()
    }

    private fun injectMembers() {
        appComponent.inject(this)
    }
}
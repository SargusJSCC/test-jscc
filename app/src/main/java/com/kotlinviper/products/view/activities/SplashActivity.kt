package com.kotlinviper.products.view.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinviper.products.contract.SplashContract
import com.kotlinviper.products.di.ApplicationComponent
import com.kotlinviper.products.presentor.SplashPresenter
import com.kotlinviper.products.router.Router
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {


    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as com.kotlinviper.products.DemoApplication).appComponent
    }

    private var presenter: SplashContract.Presenter? = null

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        presenter = SplashPresenter(this, router)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
    }


    override fun finishView() {
        finish()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun getContext(): Context {
        return this
    }
}

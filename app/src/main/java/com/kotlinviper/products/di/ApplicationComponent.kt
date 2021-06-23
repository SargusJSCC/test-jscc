package com.kotlinviper.products.di

import com.kotlinviper.products.interactor.DetailsInteractor
import com.kotlinviper.products.interactor.ListInteractor
import com.kotlinviper.products.view.activities.LoginActivity
import com.kotlinviper.products.interactor.LoginInteractor
import com.kotlinviper.products.view.activities.DetailActivity
import com.kotlinviper.products.view.activities.ListActivity
import com.kotlinviper.products.view.activities.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: com.kotlinviper.products.DemoApplication)

    fun inject(loginActivity: LoginActivity)
    fun inject(loginInteractor: LoginInteractor)

    fun inject(listInteractor: ListInteractor)
    fun inject(detailsInteractor: DetailsInteractor)

    fun inject(splashActivity: SplashActivity)
    fun inject(listActivity: ListActivity)
    fun inject(detailActivity: DetailActivity)

}

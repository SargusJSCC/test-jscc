package com.kotlinviper.products.di

import android.content.Context
import com.kotlinviper.products.data.AppDatabase
import com.kotlinviper.products.entity.Repository
import com.kotlinviper.products.router.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: com.kotlinviper.products.DemoApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesOffersRepository(): Repository {
        return Repository(AppDatabase.getInstance(application.applicationContext))
    }

    // Routing layer (VIPER)
    @Provides
    @Singleton
    fun providesRouter(): Router {
        return Router()
    }
}

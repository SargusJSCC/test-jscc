package com.kotlinviper.products.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlinviper.products.entity.Product
import org.junit.*

class AppDatabaseTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mDatabase: AppDatabase? = null

    private val myOffer = Product("111", "hrl", "nnn", "ddddd", "tttt", "cccc")
    private val myOffer2 = Product("222", "hrl", "nnn222", "ddddd222", "tttt222", "cccc222")

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java!!
        )
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase?.close()
    }

    @Test
    fun insertAndGetOfferById() {
    }

    @Test
    fun insertAndGetOffers() {

    }
}
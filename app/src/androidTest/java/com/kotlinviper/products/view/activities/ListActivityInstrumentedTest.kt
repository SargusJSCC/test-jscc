package com.kotlinviper.products.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.kotlinviper.products.R
import com.kotlinviper.products.entity.Header
import com.kotlinviper.products.entity.Repo
import com.kotlinviper.products.view.adapters.ListItemViewHolder
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class ListActivityInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<ListActivity>(ListActivity::class.java)

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    var offersRepo = Mockito.mock(Repo::class.java)

    @Before
    fun setUp() {

        //TODO this is obviously not the way to do this. The mock is getting ignored

        MockitoAnnotations.initMocks(this)

        val data = Flowable.fromArray(listOf(
            Header("111", "", "name1111", "descr111","descr111","descr111"),
            Header("222", "", "name222", "descr222","descr111","descr111"),
            Header("333", "", "name333", "descr333", "descr111","descr111")))

        Mockito.`when`(offersRepo.getOfferHeaders()).thenReturn(data)
        //TODO would also need to mock getOffer(id)
    }


    @Test
    fun testRecyclerViewIsPopulated() {

        waitForSplashScreen()

        onView(withId(R.id.offers_list_activity))
            .check(matches(hasDescendant(withText("\$0.75 Cash Back"))))
    }

    @Test
    fun testRecyclerViewItemClickLaunchesDetailActivity() {

        waitForSplashScreen()

        assert(onView(withId(R.id.offers_list_activity)) != null)

        onView(withId(R.id.rv_offers_list_activity))
            .perform(RecyclerViewActions.scrollToPosition<ListItemViewHolder>(2))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ListItemViewHolder>(2, click()))

        assert(onView(withId(R.id.offers_list_activity)) == null)

        onView(withId(R.id.name)).check(matches(withText("............")))
        onView(withId(R.id.description)).check(matches(withText(".........")))
        onView(withId(R.id.terms)).check(matches(withText("..................")))
        onView(withId(R.id.currentValue)).check(matches(withText("...............")))

    }

    private fun waitForSplashScreen() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

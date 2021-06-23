package com.kotlinviper.products.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.kotlinviper.products.R
import com.kotlinviper.products.contract.ListContract
import com.kotlinviper.products.entity.Header
import com.kotlinviper.products.presentor.ListPresenter
import com.kotlinviper.products.view.adapters.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class ListActivity : BaseActivity(), ListContract.View {

    companion object {
        val TAG: String by lazy { ListActivity::class.java.simpleName }

        fun callingIntent(context: Context): Intent {

            return Intent(context, ListActivity::class.java)
        }
    }

    private var presenter: ListContract.Presenter? = null
    private val mPrefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this@ListActivity) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setTitle(R.string.app_name)
        toolbar.setLogo(R.drawable.ic_logo)
        toolbar.setTitleTextColor(Color.WHITE);
        var islogin = mPrefs.getBoolean("isLogin", false)

        appComponent.inject(this)
        presenter = ListPresenter(this, router)

        if (!islogin) {
            presenter?.goViewLogin()
        }

        rv_products_list_activity.adapter = ListAdapter({ productHeader -> productHeader?.let {
            presenter?.productItemItemClicked(
                it
            )
        } }, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {

                val editor: SharedPreferences.Editor = mPrefs.edit()
                editor.clear().apply()
                presenter?.goViewLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showLoading() {
        rv_products_list_activity.isEnabled = false
        prog_bar_products_list_activity.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_products_list_activity.isEnabled = true
        prog_bar_products_list_activity.visibility = View.GONE
    }

    override fun showproducts(data: List<Header>) {
        (rv_products_list_activity.adapter as ListAdapter).updateData(data)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun finishView() {
        finish()
    }

}

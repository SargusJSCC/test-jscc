package com.kotlinviper.products.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kotlinviper.products.R
import com.kotlinviper.products.contract.DetailsContract
import com.kotlinviper.products.entity.Product
import com.kotlinviper.products.presentor.DetailsPresenter
import com.kotlinviper.products.utilities.product_KEY
import kotlinx.android.synthetic.main.activity_products_detail.*


class DetailActivity : BaseActivity(), DetailsContract.View {

    companion object {
        fun callingIntent(context: Context, productId: String?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(product_KEY, productId)
            return intent
        }
    }

    private var presenter: DetailsContract.Presenter? = null

    var product: Product? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setTitle(product?.title)

       // toolbar.setLogo(R.drawable.ic_logo)

        appComponent.inject(this)
        presenter = DetailsPresenter(this, router)

    }

    override fun onResume() {
        super.onResume()

        val productId = intent?.getStringExtra(product_KEY)

        presenter?.onViewCreated(productId)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun showproductData(product: Product?) {
        this.product = product
        //name.text = product?.title
        toolbar_title.text = product?.title
        description.text = product?.longDescription
        product?.image?.let { imageView.loadUrl(it) }
    }

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(20)
            .error(R.drawable.baseline_insert_photo_black_48)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                presenter?.backButtonClicked()
                true
            }
            else -> false
        }
    }
}



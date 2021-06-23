package com.kotlinviper.products.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kotlinviper.products.*
import com.kotlinviper.products.contract.LoginContracts
import com.kotlinviper.products.presentor.LoginPresenter
import com.kotlinviper.products.utilities.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_login.*

class LoginActivity : BaseActivity(), LoginContracts.View{

    companion object {

        fun callingIntent(context: Context): Intent {

            return Intent(context, LoginActivity::class.java)
        }
    }

    private var presenter: LoginContracts.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        appComponent.inject(this)
        presenter = LoginPresenter(this, router, this)

        btn_submit.setOnClickListener(View.OnClickListener {
            presenter?.onLoginButtonPressed(et_username.text.toString(),et_password.text.toString())
        })
    }

    override fun showLoading() {
        progressLogin.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressLogin.visibility = View.GONE
    }


    override fun setUserError(resId: Int) {
        et_username.error = getString(resId)
    }

    override fun setPasswordError(resId: Int) {
        et_password.error = getString(resId)
    }

    override fun showError(message: String) {
        Utils.showToast(this@LoginActivity,message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun finishView() {
        finish()
    }


}

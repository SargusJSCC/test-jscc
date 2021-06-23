package com.kotlinviper.products.interactor

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.Toast
import com.kotlinviper.products.contract.LoginContracts
import com.kotlinviper.products.data.loader.LoginResponseModel
import com.kotlinviper.products.webServices.Api
import com.kotlinviper.products.webServices.Webservice
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.set


@Suppress("DEPRECATION")
class LoginInteractor(var output: LoginContracts.InteractorOutput?, val context: Context):
    LoginContracts.Interactor {

    private val mPrefs:
            SharedPreferences by lazy{ PreferenceManager.getDefaultSharedPreferences(context) }

    init {
        com.kotlinviper.products.DemoApplication.INSTANCE.appComponent.inject(this)
    }


    @SuppressLint("CheckResult")
    override fun login(username: String, password: String) {

        output?.onProgressLogin()

        val data = HashMap<String, String>()
        data["user"] = username
        data["password"] = password

        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            JSONObject(data as Map<*, *>).toString()
        )

        val service: Api = Webservice.getInstance().getApi()
        val call: Call<LoginResponseModel> = service.login(body)
        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>, response: Response<LoginResponseModel>
            ) {
                if(response.isSuccessful and (response.body()?.status != false)){

                    val editor:SharedPreferences.Editor =  mPrefs.edit()

                    editor.putBoolean("isLogin", true)
                    editor.putString("username",username)
                    editor.putString("password",password)
                    editor.apply()
                    editor.commit()

                    output?.onLoginSuccess()
                    output?.offProgressLogin()
                }else{
                    output?.offProgressLogin()
                    Toast.makeText(context,"Credenciales incorrectas!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}


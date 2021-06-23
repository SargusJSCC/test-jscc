package com.kotlinviper.products.data.loader

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.preference.PreferenceManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kotlinviper.products.data.AppDatabase
import com.kotlinviper.products.entity.Product
import com.kotlinviper.products.webServices.Api
import com.kotlinviper.products.webServices.Webservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Arrays.asList

@Suppress("DEPRECATION")
class DatabaseWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {


    companion object {
        private val TAG by lazy { DatabaseWorker::class.java.simpleName }
    }

    override val coroutineContext = Dispatchers.IO

    private val mPrefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            context
        )
    }

    val context: Context = context

    override suspend fun doWork(): Result = coroutineScope {

            var username :
                    String = mPrefs.getString("username", "")!!
            var password :
                    String = mPrefs.getString("password", "")!!

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
                    if (response.isSuccessful and (response.body()?.status != false)) {

                        val jObject = JSONObject(response.body()?.data)

                        val jArray: JSONArray = jObject.getJSONArray("products")

                        var size_products: Int = jArray.length() - 1

                        for(i in 0..size_products) {

                            val database = AppDatabase.getInstance(applicationContext)


                            val jsonObjectHijo: JSONObject = jArray.getJSONObject(i)

                            val id = jsonObjectHijo.getString("id")
                            val path = jsonObjectHijo.getString("path")
                            val image = jsonObjectHijo.getString("image")
                            val title = jsonObjectHijo.getString("title")
                            val longDescription = jsonObjectHijo.getString("longDescription")
                            val shortDescription = jsonObjectHijo.getString("shortDescription")


                            var products =
                                Product(id, path, image, title, longDescription, shortDescription)

                            AsyncTask.execute { // Insert Data
                                AppDatabase.getInstance(context).productDao().insertAll(asList(products))

                            }

                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {

                }
            })

        Result.success()
    }
}



package com.sample.testgps

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip: String = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)

        val apiService: LocationApiService = getGeoApiService()


        apiService.getIP()?.enqueue(object : Callback<IPResponse?> {
            override fun onResponse(call: Call<IPResponse?>?, response: Response<IPResponse?>) {
                getLocation(response.body()?.ip.toString())
            }

            override fun onFailure(call: Call<IPResponse?>?, t: Throwable) {
                t.message
            }
        })
    }

    private fun getLocation(ip: String) {
        val apiService: LocationApiService = getGeoApiService()
        apiService.getLocation(ip)?.enqueue(object : Callback<LocationResponse?> {
            override fun onResponse(call: Call<LocationResponse?>?, response: Response<LocationResponse?>) {
                Log.e("mantap", response.body().toString())

            }

            override fun onFailure(call: Call<LocationResponse?>?, t: Throwable) {
                t.message
            }
        })
    }
}

interface LocationApiService {

    @GET("http://api.ipstack.com/{ip}?access_key=$API_KEY")
    fun getLocation(@Path("ip") ip: String): Call<LocationResponse?>?

    @GET("https://api.myip.com/")
    fun getIP(): Call<IPResponse>
}

const val API_KEY = "97cf8af36b4d435177e717289b8c6171"
const val BASE_URL = "http://ip-api.com/"

fun getGeoApiService(): LocationApiService {
    return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApiService::class.java)
}
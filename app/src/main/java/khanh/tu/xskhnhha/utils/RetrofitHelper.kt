package khanh.tu.xskhnhha.utils

import khanh.tu.xskhnhha.jump_code.data.remote.JumpRepo
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    var DOMAIN1 = "abcd158368.com"
    var DOMAIN2 = "sunggpalay3688.com"
    var onRetry = false
    private fun getBaseUrl(): String{
        return "https://${if(onRetry) DOMAIN2 else DOMAIN1}/jeesite/f/guestbook/"
    }

    fun service(): JumpRepo {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHeaders())
            .build()
            .create(JumpRepo::class.java)
    }

    private fun getHeaders(): OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val request: Request =
                chain.request()
                    .newBuilder()
                    .addHeader("Accept", "*/*")
                    .addHeader("Connection", "Keep-Alive")
                    .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}
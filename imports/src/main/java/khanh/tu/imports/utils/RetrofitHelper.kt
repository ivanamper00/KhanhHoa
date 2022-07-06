package khanh.tu.imports.utils

import khanh.tu.imports.data.remote.JumpRepo
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    private const val BASE_URL = "https://abcd158368.com/jeesite/f/guestbook/"

    fun service(): JumpRepo {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHeaders())
            .build()
            .create(JumpRepo::class.java)
    }

    private fun getHeaders(): OkHttpClient{
        val httpClient = OkHttpClient.Builder()
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
package com.mechanical.apiescavador.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun getRetrofit(url: String = "https://api.escavador.com/api/v1/"): Retrofit {
    return Retrofit
            .Builder()
            .client(getHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()
}

fun <T> getRetrofit(clazz: Class<T>): T {
    return getRetrofit().create(clazz)
}


private fun getHeader(accessToken : String = ""): OkHttpClient {
    val httpClient = OkHttpClient.Builder()

    httpClient.addNetworkInterceptor {
        val builder = it
                .request()
                .newBuilder()

        builder.addHeader("Authorization", "Bearer $accessToken")

        it.proceed(builder.build())
    }

    httpClient.readTimeout( 30, TimeUnit.SECONDS )

    return httpClient.build()

}

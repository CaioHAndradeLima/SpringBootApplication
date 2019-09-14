package com.mechanical.apiescavador.retrofit

import com.mechanical.apiescavador.request.LoginEscavador
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private lateinit var retrofitEscavador: Retrofit

fun providerRetrofit(url: String = "https://api.escavador.com/api/v1/"): Retrofit {
    if (::retrofitEscavador.isInitialized) {
        return retrofitEscavador
    }

    retrofitEscavador = Retrofit
            .Builder()
            .client(getHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()

    return retrofitEscavador
}

private fun getHeader(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()

    httpClient.addNetworkInterceptor {
        val builder = it
                .request()
                .newBuilder()

        LoginEscavador.getAuthorization()?.let { accessToken ->
            builder.addHeader("Authorization", "Bearer $accessToken")
        }

        it.proceed(builder.build())
    }

    httpClient.readTimeout(30, TimeUnit.SECONDS)

    return httpClient.build()

}

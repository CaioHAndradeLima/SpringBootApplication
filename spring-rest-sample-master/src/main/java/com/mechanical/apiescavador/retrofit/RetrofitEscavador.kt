package com.mechanical.apiescavador.retrofit

private val retrofitMethods: RequestRetrofitMethods = providerRetrofit()
        .create(RequestRetrofitMethods::class.java)

private val retrofitLink: RequestRetrofitLink  = providerRetrofit()
        .create(RequestRetrofitLink::class.java)

fun provideRetrofitEscavador(): RequestRetrofitMethods {
    return retrofitMethods
}

fun provideRetrofitEscavadorWithoutLink(): RequestRetrofitLink {
    return retrofitLink
}


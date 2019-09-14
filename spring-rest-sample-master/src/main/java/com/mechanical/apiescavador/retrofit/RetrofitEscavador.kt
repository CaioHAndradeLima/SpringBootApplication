package com.mechanical.apiescavador.retrofit

private lateinit var retrofitMethods: RequestRetrofitMethods
private lateinit var retrofitLink: RequestRetrofitLink

fun provideRetrofitEscavador(): RequestRetrofitMethods {
    if (!::retrofitMethods.isInitialized)
        return retrofitMethods

    retrofitMethods = providerRetrofit()
            .create(RequestRetrofitMethods::class.java)

    return retrofitMethods
}

fun provideRetrofitEscavadorWithoutLink(): RequestRetrofitLink {
    if (!::retrofitLink.isInitialized)
        return retrofitLink

    retrofitLink = providerRetrofit()
            .create(RequestRetrofitLink::class.java)

    return retrofitLink
}


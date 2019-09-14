package com.mechanical.apiescavador.manager

import com.mechanical.apiescavador.request.LoginEscavador
import com.mechanical.apiescavador.request.LoginEscavador.isAuthorizedNewRequests
import com.mechanical.apiescavador.retrofit.RequestRetrofitMethods
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavador


fun requestApiEscavador(call: (RequestRetrofitMethods) -> Unit) {
    var isAuthorizedNewRequests = isAuthorizedNewRequests()

    if (!isAuthorizedNewRequests) {
        isAuthorizedNewRequests = LoginEscavador.requestToken()
    }

    if (isAuthorizedNewRequests) {
        call(provideRetrofitEscavador())
    }
}
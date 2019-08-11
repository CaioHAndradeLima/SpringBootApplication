package com.mechanical.apiescavador.retrofit

import com.mechanical.apiescavador.`in`.UserEscavador
import com.mechanical.apiescavador.out.AuthenticationEscavadorModel


val userEscavador = UserEscavador()
lateinit var infoAuthentication: AuthenticationEscavadorModel


fun requestToken() {
    getRetrofit()
            .create(RequestRetrofitMethods::class.java)
            .requestToken(userEscavador)
            .subscribe {

                if (it.isSuccessful && it.body() != null) {
                    infoAuthentication = it.body()!!
                    System.out.println("foi recebido token")
                } else {
                    System.out.println("nao foi recebido token")
                }
            }
}



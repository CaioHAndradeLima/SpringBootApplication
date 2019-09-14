package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.`in`.UserEscavador
import com.mechanical.apiescavador.out.AuthenticationEscavadorModel
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavador
import com.mechanical.cassandraRepository.extensions.newLog
import java.util.*

object LoginEscavador {
    private val userEscavador = UserEscavador()
    private lateinit var infoAuthentication: AuthenticationEscavadorModel

    fun requestToken(): Boolean {
        var isAuthorizedNewRequests = false

        provideRetrofitEscavador()
                .requestToken(userEscavador)
                .subscribe({
                    if (it.isSuccessful && it.body() != null) {
                        infoAuthentication = it.body()!!
                        isAuthorizedNewRequests = true
                    }
                }) {
                    it.newLog()
                }

        return isAuthorizedNewRequests
    }

    fun isAuthorizedNewRequests(): Boolean {
        return LoginEscavador::infoAuthentication.isInitialized &&
                Date().time > infoAuthentication.expires_in
    }

    fun getAuthorization(): String? {
        if(!LoginEscavador::infoAuthentication.isInitialized)
            return null

        return infoAuthentication.access_token
    }

}
package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavadorWithoutLink
import com.mechanical.cassandraRepository.extensions.newLog
import com.mechanical.isDebug
import com.mechanical.provider.provideGson

object ProcessEscavador {

    fun requestProcessByLink(linkApi: String): ProcessEscavadorModel? {

        var process: ProcessEscavadorModel? = null


        provideRetrofitEscavadorWithoutLink()
                .requestProcess(linkApi)
                .subscribe({
                    val body = it.body()

                    if (it.isSuccessful && body != null) {

                        process = body
                    } else if (isDebug) {
                        System.out.println("class ManagerProcessEscavador.requestToEscavadorSearchNewProcess response code= ${it.code()} and response message= ${it.message()}")
                    }

                }) {
                    it.newLog()
                }


        return process
    }
}
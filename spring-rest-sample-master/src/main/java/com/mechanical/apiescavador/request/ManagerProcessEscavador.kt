package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.out.ManagerProcessEscavadorModel
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavador
import com.mechanical.cassandraRepository.extensions.newLog

object ManagerProcessEscavador {

    //its could be assync or not assync
    fun requestToEscavadorSearchNewProcess(numberProcesss: String): ManagerProcessEscavadorModel? {

        var processInfoManager: ManagerProcessEscavadorModel? = null

        provideRetrofitEscavador()
                .requestInfosToGetProcess(numberProcesss)
                .subscribe({

                    val body = it.body()

                    if (it.isSuccessful && body != null) {

                        processInfoManager = body
                    } else if (isDebug) {
                        System.out.println("class ManagerProcessEscavador.requestToEscavadorSearchNewProcess response code= ${it.code()} and response message= ${it.message()}")
                    }

                }) {
                    it.newLog()
                }

        return processInfoManager
    }
}


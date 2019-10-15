package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.`in`.ProcessMonitoringIn
import com.mechanical.apiescavador.out.ManagerProcessEscavadorOut
import com.mechanical.apiescavador.out.ProcessMonitoringOut
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavador
import com.mechanical.cassandraRepository.extensions.newLog
import com.mechanical.isDebug

object ManagerProcessEscavador {

    //its could be assync or not assync
    fun requestToEscavadorSearchNewProcess(numberProcesss: String): ManagerProcessEscavadorOut? {

        var processInfoManager: ManagerProcessEscavadorOut? = null

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

    fun requestToEscavadorAddMonitorization(numberProcesss: String): ProcessMonitoringOut? {
        var processMonitoringOut : ProcessMonitoringOut? = null

        provideRetrofitEscavador()
                .addMonitoring(ProcessMonitoringIn(valor = numberProcesss))
                .subscribe({
                    val body = it.body()

                    if (it.isSuccessful && body != null) {

                        processMonitoringOut = body
                    } else if (isDebug) {
                        System.out.println("class ManagerProcessEscavador.requestToEscavadorSearchNewProcess response code= ${it.code()} and response message= ${it.message()}")
                    }

                }) {
                    it.newLog()
                }

        return processMonitoringOut
    }
}


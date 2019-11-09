package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.`in`.FREQUENCY
import com.mechanical.apiescavador.`in`.ProcessMonitoringIn
import com.mechanical.apiescavador.manager.requestApiEscavador
import com.mechanical.apiescavador.out.ManagerProcessEscavadorOut
import com.mechanical.apiescavador.out.ProcessMonitoringOut
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavador
import com.mechanical.cassandraRepository.extensions.newLog
import com.mechanical.isDebug
import com.mechanical.provider.provideGson

object ManagerProcessEscavador {

    //its could be assync or not assync
    fun requestToEscavadorSearchNewProcess(numberProcesss: String): ManagerProcessEscavadorOut? {

        var processInfoManager: ManagerProcessEscavadorOut? = null

        requestApiEscavador {
            it.requestInfosToGetProcess(numberProcesss)
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
        }

        //return processInfoManager

        return provideGson()
                .fromJson(json,ManagerProcessEscavadorOut::class.java)
    }
    val json = "{\n" +
            "  \"id\": 40,\n" +
            "  \"numero_processo\": \"0000000-00.2019.8.26.0000\",\n" +
            "  \"status\": \"PENDENTE\",\n" +
            "  \"resposta\": null,\n" +
            "  \"enviar_callback\": \"SIM\",\n" +
            "  \"status_callback\": null,\n" +
            "  \"link_api\": \"https://api.escavador.com/api/v1/async/resultados/40\",\n" +
            "  \"created_at\": {\n" +
            "    \"date\": \"2019-02-27 20:58:00.000000\",\n" +
            "    \"timezone_type\": 3,\n" +
            "    \"timezone\": \"UTC\"\n" +
            "  }\n" +
            "}"

    fun requestToEscavadorAddMonitoring(numberProcesss: String, frequencyMonitoring: FREQUENCY): ProcessMonitoringOut? {
        var processMonitoringOut: ProcessMonitoringOut? = null

        requestApiEscavador {
            it.addMonitoring(ProcessMonitoringIn(valor = numberProcesss, frequencia = frequencyMonitoring))
                    .subscribe({
                        val body = it.body()

                        if (it.isSuccessful && body != null) {

                            processMonitoringOut = body
                        } else if (isDebug) {
                            System.out.println("class ManagerProcessEscavador.requestToEscavadorAddMonitoring response code= ${it.code()} and response message= ${it.message()}")
                        }

                    }) {
                        it.newLog()
                    }
        }

        return processMonitoringOut
    }

    fun requestToEscavadorRemoveMonitoring(id: Int): Boolean {
        var isSuccessful = false

        requestApiEscavador {
            it.removeMonitoring(id)
                .subscribe({
                    isSuccessful = it.isSuccessful
                }) {
                    it.newLog()
                }
        }

        return isSuccessful
    }
}


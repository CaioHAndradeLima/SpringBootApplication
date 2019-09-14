package com.mechanical.integration

import com.mechanical.apiescavador.request.ManagerProcessEscavador
import com.mechanical.cassandraRepository.model.ManagerProcessCassandraModel

object ManagerProcessIntegration {

    fun requestManagerProcess(numberProcess: String): ManagerProcessCassandraModel? {
        val processManagerEscavador = ManagerProcessEscavador
                .requestToEscavadorSearchNewProcess(numberProcess) ?: return null

        return ManagerProcessCassandraModel(processManagerEscavador)
    }
}
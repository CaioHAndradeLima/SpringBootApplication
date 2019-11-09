package com.mechanical.integration

import com.mechanical.apiescavador.`in`.FREQUENCY
import com.mechanical.apiescavador.manager.requestApiEscavador
import com.mechanical.apiescavador.out.ProcessMonitoringOut
import com.mechanical.apiescavador.request.ManagerProcessEscavador
import com.mechanical.cassandraRepository.model.ManagerProcessCassandraModel

object ManagerProcessIntegration {

    fun requestManagerProcess(numberProcess: String): ManagerProcessCassandraModel? {

        val processManagerEscavador = ManagerProcessEscavador
                .requestToEscavadorSearchNewProcess(numberProcess) ?: return null

        return ManagerProcessCassandraModel(processManagerEscavador)
    }

    fun addMonitoring(numberProcess: String, frequencyMonitoring: FREQUENCY): ProcessMonitoringOut? {
        return ManagerProcessEscavador
                .requestToEscavadorAddMonitoring(numberProcess,frequencyMonitoring) ?: return null
    }

    fun removeMonitoring(id: Int): Boolean {
        return ManagerProcessEscavador
                .requestToEscavadorRemoveMonitoring(id)
    }

}
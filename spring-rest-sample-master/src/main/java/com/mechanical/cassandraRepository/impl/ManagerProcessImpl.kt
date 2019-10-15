package com.mechanical.cassandraRepository.impl

import com.google.gson.annotations.SerializedName
import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.cassandraRepository.model.ManagerProcessCassandraModel
import com.mechanical.cassandraRepository.model.StatusManagerProcess
import com.mechanical.cassandraRepository.repository.ManagerProcessRepository
import com.mechanical.endpoint.LoginEntity
import com.mechanical.integration.ManagerProcessIntegration
import com.mechanical.integration.ProcessIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ManagerProcessImpl {

    @Autowired(required = true)
    lateinit var managerProcessRepository: ManagerProcessRepository

    fun save(process: ManagerProcessCassandraModel) {
        managerProcessRepository.save(process)
    }

    fun searchProcess(numberProcess: String): ManagerProcessResponse? {
        val managerProcessCassandraModel = ManagerProcessIntegration.requestManagerProcess(numberProcess)

        if (managerProcessCassandraModel != null) {
            save(managerProcessCassandraModel)

            managerProcessCassandraModel.status?.let {

                if (it == StatusManagerProcess.SUCESSO || it == StatusManagerProcess.PENDENTE) {

                    val process = ProcessIntegration.requestProcessByLink(managerProcessCassandraModel.linkApi)
                    return ManagerProcessResponse(process, managerProcessCassandraModel)
                }
            }

            return ManagerProcessResponse(null, managerProcessCassandraModel)
        }

        return managerProcessCassandraModel
    }

    /**
     * register process monitoring and return true if it's on monitoring else otherwise
     */
    fun registerMonitoringProccess(infoLogin: LoginEntity, validNumberProcess: String) : Boolean {

        val infoProcess = ManagerProcessIntegration.addMonitoring(validNumberProcess)
    }
}

data class ManagerProcessResponse(
        @SerializedName("process") var process: ProcessEscavadorModel?,
        @SerializedName("processInfo") var processInfo: ManagerProcessCassandraModel
)

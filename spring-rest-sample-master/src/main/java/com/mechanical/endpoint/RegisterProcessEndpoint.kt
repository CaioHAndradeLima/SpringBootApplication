package com.mechanical.endpoint

import com.mechanical.apiescavador.`in`.FREQUENCY
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToProcessCassandraModel
import com.mechanical.cassandraRepository.impl.*
import com.mechanical.endpoint.view.model.RegisterProcessOut
import com.mechanical.model.ProcessNumberIn
import com.mechanical.provider.UserProvider
import com.mechanical.provider.UserProvider.provideLoggedLawOffice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/registerProcess")
class RegisterProcessEndpoint {

    @Autowired
    lateinit var processManagerImpl: ProcessMonitoringImpl

    @Autowired
    lateinit var managerProcess: ManagerProcessImpl

    @Autowired
    lateinit var eventImpl: EventCassandraImpl

    @Autowired
    lateinit var lawOfficeImpl: LawOfficeImpl

    @Autowired
    lateinit var processImpl: ProcessCassandraImpl

    @PostMapping
    fun registerProcess(@RequestBody jsonProcessNumber: String): ResponseEntity<*> {
        return managerRequest<ProcessNumberIn, RegisterProcessOut>(jsonProcessNumber) { it, user ->
            val processNumber = it!!.processNumber

            val registerProcessOut = RegisterProcessOut(errorExternalApi = true)

            if (processManagerImpl.isMonitoring(processNumber, UserProvider.provideIdIdentifierToProcessEvents(lawOfficeImpl))) {
                registerProcessOut.errorExternalApi = false
                registerProcessOut.wasRegisteredToSeeMovementOfThisProcess = true
                registerProcessOut.wasFoundThisProcess = true
                return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), registerProcessOut)
            }

            val processInfo = managerProcess.searchProcess(processNumber)
                    ?: return@managerRequest Pair(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT), registerProcessOut)

            registerProcessOut.errorExternalApi = false

            processInfo.process?.let { processEscavadorModel ->
                processEscavadorModel.resposta?.let {respostaEscavador ->
                    processImpl.save(convertProcessEscavadorModelToProcessCassandraModel(processEscavadorModel.numero_processo, respostaEscavador))
                    eventImpl.addNewEvents(processEscavadorModel)

                    registerProcessOut.wasFoundThisProcess = true

                    if (it.isNeededRegister) {
                        val monitoringIsRegistered = processManagerImpl.registerMonitoringProccess(
                                provideLoggedLawOffice(user.user, lawOfficeImpl),
                                processEscavadorModel.numero_processo,
                                it.frequency ?: FREQUENCY.SEMANAL
                        )

                        registerProcessOut.wasRegisteredToSeeMovementOfThisProcess = monitoringIsRegistered
                    }
                }
            }

            Pair(ResponseEntity.status(HttpStatus.OK), registerProcessOut)
        }
    }

    companion object {
        @Bean
        fun getProcessMonitoringImpl() = ProcessMonitoringImpl()

        @Bean
        fun getManagerProcessImpl() = ManagerProcessImpl()

        @Bean
        fun getEventProcessCassandraImpl() = EventProcessCassandraImpl()

        @Bean
        fun getProcessCassandraImpl() = ProcessCassandraImpl()
    }
}
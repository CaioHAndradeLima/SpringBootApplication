package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.EventCassandraImpl
import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import com.mechanical.cassandraRepository.impl.ManagerProcessImpl
import com.mechanical.cassandraRepository.impl.ProcessMonitoringImpl
import com.mechanical.model.ProcessNumberIn
import com.mechanical.provider.UserProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/registerProcess")
class RegisterProcessEndpoint {

    @Autowired
    lateinit var processManagerImpl : ProcessMonitoringImpl

    @Autowired
    lateinit var managerProcess: ManagerProcessImpl

    @Autowired
    lateinit var eventImpl: EventCassandraImpl

    @Autowired
    lateinit var lawOfficeImpl: LawOfficeImpl

    @PostMapping
    fun registerProcess(jsonProcessNumber: String): ResponseEntity<*> {
        return managerRequest<ProcessNumberIn,Unit?>(jsonProcessNumber) {  it, user ->
            val processNumber = it!!.processNumber

            if(processManagerImpl.isMonitoring(processNumber, UserProvider.provideIdIdentifierToProcessEvents(lawOfficeImpl))) {
                return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), null)
            }

            val processInfo = managerProcess.searchProcess(processNumber)
                    ?: return@managerRequest Pair(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT), null)

            processInfo.process?.let {
                eventImpl.addNewEvents(it)
            }

            Pair(ResponseEntity.status(HttpStatus.OK), null)
        }
    }

    companion object {
        @Bean
        fun getProcessMonitoringImpl() = ProcessMonitoringImpl()

        @Bean
        fun getManagerProcessImpl() = ManagerProcessImpl()
    }
}
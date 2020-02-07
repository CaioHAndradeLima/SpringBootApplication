package com.mechanical.endpoint

import com.mechanical.apiescavador.`in`.FREQUENCY
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToProcessCassandraModel
import com.mechanical.cassandraRepository.impl.*
import com.mechanical.cassandraRepository.model.StatusManagerProcess
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.endpoint.view.model.RegisterProcessOut
import com.mechanical.integration.ProcessIntegration
import com.mechanical.model.ProcessNumberIn
import com.mechanical.model.RoutineModel
import com.mechanical.model.UserModel
import com.mechanical.provider.UserProvider
import com.mechanical.provider.UserProvider.provideLoggedLawOffice
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

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

            Companion.eventImpl = eventImpl
            Companion.processManagerImpl = processManagerImpl
            Companion.managerProcess = managerProcess
            Companion.lawOfficeImpl = lawOfficeImpl
            Companion.processImpl = processImpl

            if (processManagerImpl.isMonitoring(processNumber, UserProvider.provideIdIdentifierToProcessEvents(lawOfficeImpl, user.user))) {
                val registerProcessOut = RegisterProcessOut(wasFoundThisProcess = true)
                return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), registerProcessOut)
            }

            val processInfo = managerProcess.searchProcess(processNumber)
                    ?: return@managerRequest Pair(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT), RegisterProcessOut(wasFoundThisProcess = false))

            if (searchProcessAndSaveIt(isNeededRegister = it.isNeededRegister, processInfo = processInfo, user = user.user, frequency = it.frequency) == 1) {
                Pair(ResponseEntity.status(HttpStatus.OK), RegisterProcessOut(wasFoundThisProcess = true))
            } else {
                Pair(ResponseEntity.status(HttpStatus.PROCESSING), RegisterProcessOut(wasFoundThisProcess = false))
            }
        }
    }


    companion object {

        lateinit var processManagerImpl: ProcessMonitoringImpl

        lateinit var managerProcess: ManagerProcessImpl

        lateinit var eventImpl: EventCassandraImpl

        lateinit var lawOfficeImpl: LawOfficeImpl

        lateinit var processImpl: ProcessCassandraImpl

        private val listRoutine: MutableList<RoutineModel> = mutableListOf()


        /**
         * search the process by link and register monitoring if needed
         *
         * @return 1 when success, 2 when error connection, 3 when error api
         */
        private fun searchProcessAndSaveIt(processInfo: ManagerProcessResponse,
                                           isNeededRegister: Boolean,
                                           user: UserCassandraModel,
                                           frequency: FREQUENCY?,
                                           addToRotineWhenStatusIsPendente: Boolean = true): Int {


            //FIXME CONFIRMAR STATUS DE ERRO
            if (processInfo.processInfo.status == StatusManagerProcess.ERRO) {
                return 3
            } else if (processInfo.processInfo.status == StatusManagerProcess.PENDENTE) {
                if (addToRotineWhenStatusIsPendente) {
                    listRoutine.add(RoutineModel(
                            processInfo,
                            isNeededRegister,
                            user,
                            frequency
                    ))
                }
            } else if (processInfo.processInfo.status == StatusManagerProcess.SUCESSO) {
                if (processInfo.process == null || processInfo.process!!.resposta == null) {
                    throw IllegalStateException("processEscavadorModel.resposta == null when processEscavadorModel.status == SUCESSO")
                }
            }


            processInfo.process?.let { processEscavadorModel ->
                processEscavadorModel.resposta?.let { respostaEscavador ->
                    processImpl.save(convertProcessEscavadorModelToProcessCassandraModel(processEscavadorModel.numero_processo, respostaEscavador))
                    eventImpl.addNewEvents(processEscavadorModel, user)

                    if (isNeededRegister) {
                        val monitoringIsRegistered = processManagerImpl.registerMonitoringProccess(
                                provideLoggedLawOffice(user, lawOfficeImpl),
                                processEscavadorModel.numero_processo,
                                frequency ?: FREQUENCY.SEMANAL
                        )
                    }

                    return 1
                }
            }

            return 2
        }

        fun routine() {
            Single
                    .fromCallable { Unit }
                    .observeOn(Schedulers.newThread())
                    .delay(1, TimeUnit.MINUTES, Schedulers.newThread())
                    .subscribe({
                        for (routineModel in listRoutine) {

                            val process = ProcessIntegration.requestProcessByLink(routineModel.processInfo.processInfo.linkApi)
                            routineModel.processInfo = ManagerProcessResponse(process, routineModel.processInfo.processInfo)

                            if (process != null)
                                routineModel.processInfo.processInfo.status = process.status

                            val resultCode = searchProcessAndSaveIt(
                                    routineModel.processInfo,
                                    routineModel.isNeededRegister,
                                    routineModel.user,
                                    routineModel.frequency,
                                    false
                            )

                            if (resultCode == 1 || resultCode == 3) {
                                listRoutine.remove(routineModel)
                            }
                        }

                        routine()
                    }) {
                        routine()
                        it.printStackTrace()
                    }
        }


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
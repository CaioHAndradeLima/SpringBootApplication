package com.mechanical.cassandraRepository.impl

import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.model.ProcessMonitoringCassandraModel
import com.mechanical.cassandraRepository.repository.ProcessMonitoringRepository
import com.mechanical.infix_utils.toJson
import com.mechanical.integration.ManagerProcessIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class ProcessMonitoringImpl {

    @Autowired(required = true)
    lateinit var processMonitoringRepository: ProcessMonitoringRepository

    fun isMonitoring(numberProcess: String): Boolean {
        return processMonitoringRepository.findByNumberProcess(numberProcess) != null
    }

    fun isMonitoring(numberProcess: String, ids: MutableList<String>): Boolean {
        val processMonitoring = processMonitoringRepository.findByNumberProcess(numberProcess)
                ?: return false

        ids.forEach {
            if(processMonitoring.listWhoListeningProcess!!.contains(it))
                return true
        }

        return false
    }

    private fun addNewMonitoring(
            lawOffice: LawOffice,
            numberProcess: String,
            idMonitoring: Int
    ) {

        fun getNewMonitoringProcess() = MonitoringProcess(lawOffice.uuid.toString()).toJson()

        val processMonitoring = processMonitoringRepository.findByNumberProcess(numberProcess)

        if (processMonitoring != null) {
            val mutableSet = processMonitoring.listWhoListeningProcess!!.toHashSet()
            mutableSet.add(getNewMonitoringProcess())
            processMonitoring.listWhoListeningProcess = mutableSet

            processMonitoringRepository.save(
                    processMonitoring
            )

            return
        }

        val listWhoMonitoring = HashSet<String>()
        listWhoMonitoring.add(getNewMonitoringProcess())

        processMonitoringRepository.save(
                ProcessMonitoringCassandraModel(
                        numberProcess = numberProcess,
                        idMonitoring = idMonitoring,
                        listWhoListeningProcess = listWhoMonitoring
                )
        )
    }

    /**
     * register process monitoring and return true if it's on monitoring else otherwise
     */
    fun registerMonitoringProccess(lawOffice: LawOffice, validNumberProcess: String): Boolean {

        val infoProcess = ManagerProcessIntegration.addMonitoring(validNumberProcess)

        infoProcess?.let {
            addNewMonitoring(
                    lawOffice,
                    validNumberProcess,
                    infoProcess.id
            )
            return@registerMonitoringProccess true
        }

        return false
    }

    fun removeMonitoringProcess(lawOffice: LawOffice, validNumberProcess: String): Boolean {

        processMonitoringRepository.findByNumberProcess(validNumberProcess)?.let {
            val monitoringProces = MonitoringProcess(
                    cpfOrIdLawOffice = lawOffice.uuid.toString()
            )

            val elementJson = monitoringProces.toJson()

            if (!it.listWhoListeningProcess!!.contains(elementJson)) {
                throw IllegalStateException("Law office trying remove monitoring but no registered on data base")
            }

            val mutableSet = it.listWhoListeningProcess!!.toHashSet()
            mutableSet.remove(elementJson)

            if (mutableSet.size == 0) {
                val isSuccessful = ManagerProcessIntegration.removeMonitoring(it.idMonitoring)
                if (isSuccessful) {
                    processMonitoringRepository.delete(it)
                } else {
                    return@removeMonitoringProcess false
                }

            } else {
                it.listWhoListeningProcess = mutableSet
                processMonitoringRepository.save(it)
            }

            return@removeMonitoringProcess true
        }

        return false

    }

}

data class MonitoringProcess(
        @SerializedName("id")
        val cpfOrIdLawOffice: String,
        @SerializedName("whoSearching")
        val whoSearching: SearchType = SearchType.LAW_OFFICE
)

enum class SearchType {
    LAW_OFFICE, PERSON
}
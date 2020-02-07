package com.mechanical.cassandraRepository.impl

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToAudiencias
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToMovimentations
import com.mechanical.cassandraRepository.extensions.formatToDate
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.cassandraRepository.model.event.EventCassandraModel
import com.mechanical.cassandraRepository.model.event.EventProcessCassandraModel
import com.mechanical.cassandraRepository.model.event.EventProcessInfoModel
import com.mechanical.cassandraRepository.model.event.TypeEvent
import com.mechanical.cassandraRepository.model.event.modelEvent.Audiencia
import com.mechanical.cassandraRepository.model.event.modelEvent.Movimentation
import com.mechanical.cassandraRepository.repository.EventCassandraRepository
import com.mechanical.infix_utils.toJson
import com.mechanical.provider.UserProvider.provideIdIdentifierToProcessEvents
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
open class EventCassandraImpl {

    @Autowired(required = true)
    lateinit var eventRepository: EventCassandraRepository

    @Autowired(required = true)
    lateinit var eventProcessImpl: EventProcessCassandraImpl

    @Autowired(required = true)
    lateinit var lawOfficeImpl: LawOfficeImpl

    fun searchEvents(idIdentifier: String, lastSeen: Long? = null): MutableList<EventProcessCassandraModel>? {
        val listEvent = (if (lastSeen != null) {
            eventRepository.findToShow(idIdentifier, lastSeen)
        } else {
            eventRepository.findToShow(idIdentifier)
        }) ?: return null

        return eventProcessImpl.findToShow(listEvent)
    }

    fun addNewEvents(processEscavador: ProcessEscavadorModel, user: UserCassandraModel) {
        val listIdentifier = provideIdIdentifierToProcessEvents(lawOfficeImpl, user)
        val listAudiencias = convertProcessEscavadorModelToAudiencias(processEscavador);
        val listMovimentations = convertProcessEscavadorModelToMovimentations(processEscavador)

        listAudiencias?.let { saveEvent(it.first, it.second, listIdentifier, TypeEvent.AUDIENCIA) }
        listMovimentations?.let { saveEvent(it.first, it.second, listIdentifier, TypeEvent.MOVIMENTATION) }
    }

    private fun <T> saveEvent(numberProcess: String,
                              listEvent: MutableList<T>,
                              listIdentifier: MutableList<String>,
                              typeEvent: TypeEvent) {
        listEvent.forEach { obj ->
            listIdentifier.forEach { idIdentifier ->

                val dateEvent = when (typeEvent) {
                    TypeEvent.AUDIENCIA -> (obj as Audiencia).date.formatToDate().time
                    TypeEvent.MOVIMENTATION -> (obj as Movimentation).date.formatToDate().time
                }

                val uuidEventProcess = UUID.randomUUID()

                eventProcessImpl.save(
                        EventProcessCassandraModel(
                                numberProcess,
                                dateEvent,
                                uuidEventProcess,
                                obj.toJson(),
                                typeEvent
                        )
                )

                eventRepository.save(
                        EventCassandraModel(
                                idIdentifier,
                                dateEvent,
                                numberProcess,
                                EventProcessInfoModel(uuid = uuidEventProcess)
                        )
                )
            }
        }
    }
}
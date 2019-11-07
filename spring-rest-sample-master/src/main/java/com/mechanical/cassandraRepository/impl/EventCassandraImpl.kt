package com.mechanical.cassandraRepository.impl

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToAudiencias
import com.mechanical.cassandraRepository.convert.convertProcessEscavadorModelToMovimentations
import com.mechanical.cassandraRepository.extensions.formatToDate
import com.mechanical.cassandraRepository.model.event.EventCassandraModel
import com.mechanical.cassandraRepository.model.event.TypeEvent
import com.mechanical.cassandraRepository.repository.EventCassandraRepository
import com.mechanical.infix_utils.toJson
import com.mechanical.provider.UserProvider.provideIdIdentifierToProcessEvents
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class EventCassandraImpl {

    @Autowired(required = true)
    lateinit var eventRepository: EventCassandraRepository

    @Autowired(required = true)
    lateinit var lawOfficeImpl: LawOfficeImpl

    fun searchEvents(idIdentifier: String, lastSeen: Long? = null): MutableList<EventCassandraModel>? {
        lastSeen?.let {
            return eventRepository.findToShow(idIdentifier, it)
        }

        return eventRepository.findToShow(idIdentifier)
    }

    fun addNewEvents(processEscavador: ProcessEscavadorModel) {
        val listIdentifier = provideIdIdentifierToProcessEvents(lawOfficeImpl)


        val listAudiencias = convertProcessEscavadorModelToAudiencias(processEscavador);
        val listMovimentations = convertProcessEscavadorModelToMovimentations(processEscavador)

        listAudiencias?.let {
            it.second.forEach { audiencia ->
                listIdentifier.forEach { idIdentifier ->
                    eventRepository.save(
                            EventCassandraModel(
                                    idIdentifier,
                                    audiencia.date.formatToDate().time,
                                    audiencia.toJson(),
                                    it.first,
                                    TypeEvent.AUDIENCIA
                            )
                    )
                }
            }
        }

        listMovimentations?.let {
            it.second.forEach { movimentation ->
                listIdentifier.forEach { idIdentifier ->
                    eventRepository.save(
                            EventCassandraModel(
                                    idIdentifier,
                                    movimentation.date.formatToDate().time,
                                    movimentation.toJson(),
                                    it.first,
                                    type = TypeEvent.MOVIMENTATION
                            )
                    )
                }
            }
        }
    }
}
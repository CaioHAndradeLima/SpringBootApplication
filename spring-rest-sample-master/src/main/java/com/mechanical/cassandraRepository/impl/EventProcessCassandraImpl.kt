package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.model.event.EventCassandraModel
import com.mechanical.cassandraRepository.model.event.EventProcessCassandraModel
import com.mechanical.cassandraRepository.repository.EventProcessRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventProcessCassandraImpl {
    @Autowired(required = true)
    lateinit var eventProcessRepository: EventProcessRepository

    fun save(eventProcessCassandraModel: EventProcessCassandraModel) {
        eventProcessRepository.save(eventProcessCassandraModel)
    }

    fun findToShow(listEvents: MutableList<EventCassandraModel>): MutableList<EventProcessCassandraModel> {
        return MutableList(listEvents.size) { index ->
            val currentEvent = listEvents[index]
            eventProcessRepository.findByNumberProcessAndDateEventAndUuid(
                    currentEvent.numberProcess,
                    currentEvent.dateEvent,
                    currentEvent.getEventProcessInfoModel().uuid
            )
        }
    }
}
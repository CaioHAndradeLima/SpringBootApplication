package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.event.EventProcessCassandraModel
import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.*

interface EventProcessRepository : CassandraRepository<EventProcessCassandraModel, String> {

    fun findByNumberProcessAndDateEventAndUuid(numberProcess: String, dateEvent: Long, uuid: UUID) : EventProcessCassandraModel
}
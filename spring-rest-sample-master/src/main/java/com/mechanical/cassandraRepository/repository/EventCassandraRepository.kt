package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.event.COLUMN_DATE_EVENT
import com.mechanical.cassandraRepository.model.event.COLUMN_ID_IDENTIFIER
import com.mechanical.cassandraRepository.model.event.EventCassandraModel
import com.mechanical.cassandraRepository.model.event.NAME_TABLE
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventCassandraRepository : CassandraRepository<EventCassandraModel, String> {


    @Query("SELECT * FROM ${NAME_TABLE} WHERE ${COLUMN_ID_IDENTIFIER} = ?0 LIMIT 25")
    fun findToShow(idIdentifier: String): MutableList<EventCassandraModel>?

    @Query("SELECT * FROM ${NAME_TABLE} WHERE ${COLUMN_ID_IDENTIFIER} = ?0 AND ${COLUMN_DATE_EVENT} = ?1 LIMIT 25")
    fun findToShow(idIdentifier: String, lastSeen: Long): MutableList<EventCassandraModel>?
}

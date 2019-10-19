package com.mechanical.cassandraRepository.model

import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table
data class ProcessMonitoringCassandraModel(
        @PrimaryKeyColumn(name = "numberProcess", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        val numberProcess:String,
        val idMonitoring: Int,
        var listWhoListeningProcess: Set<String>?
)
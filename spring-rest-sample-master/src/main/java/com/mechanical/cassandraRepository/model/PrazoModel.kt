package com.mechanical.cassandraRepository.model

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.*

data class PrazoModel(
        @PrimaryKeyColumn(name = "uuid", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        @CassandraType(type = DataType.Name.UUID)
        var uuid: UUID,
        var uuidProcess: UUID,
        var processNumber: String,
        var dateToDelivery: Date?
)
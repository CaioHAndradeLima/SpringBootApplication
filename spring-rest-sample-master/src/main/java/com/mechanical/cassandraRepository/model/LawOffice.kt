package com.mechanical.cassandraRepository.model

import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*


@Table
data class LawOffice(
        @PrimaryKeyColumn(name = "cpfOwner", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        var cpfOwner: String,

        @PrimaryKeyColumn(name = "uuid", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var uuid: UUID,

        @PrimaryKeyColumn(name = "latitude", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var latitude: Double,

        @PrimaryKeyColumn(name = "longitude", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var longitude: Double,

        var name: String,

        @Transient
        var listEmployees: Set<String>?,

        var street: String,
        var CEP: String,
        var neighborhood: String,
        var identifierHome: String,
        var state: String
) {
}



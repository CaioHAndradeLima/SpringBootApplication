package com.mechanical.cassandraRepository.model

import com.google.gson.annotations.SerializedName
import com.mechanical.infix_utils.toJson
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("work")
data class WorkerCassandraModel constructor(
        @PrimaryKeyColumn(name = "profession", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        var profession: String,
        @PrimaryKeyColumn(name = "email", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var email: String,
        @PrimaryKeyColumn(name = "latitude", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var latitude: Double,
        @PrimaryKeyColumn(name = "longitude", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var longitude: Double,

        var password: String,
        @Transient var listFiles: Set<String>?
)


data class Work(
        @SerializedName("uuid") val uuid: String,
        @SerializedName("name") val name: String
)
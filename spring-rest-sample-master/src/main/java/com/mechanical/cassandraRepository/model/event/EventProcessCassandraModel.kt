package com.mechanical.cassandraRepository.model.event

import com.google.gson.annotations.SerializedName
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("eventProcess")
data class EventProcessCassandraModel(
        @SerializedName("numberProcess")
        @PrimaryKeyColumn(name = "numberProcess", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        val numberProcess: String,

        @SerializedName("dateEvent")
        @PrimaryKeyColumn(name = COLUMN_DATE_EVENT, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        val dateEvent: Long,

        @SerializedName("uuid")
        @PrimaryKeyColumn(name = "uuid", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        val uuid: UUID,

        @SerializedName("value")
        @PrimaryKeyColumn(name = "value", type = PrimaryKeyType.CLUSTERED)
        val value: String,

        @SerializedName("type")
        val type: TypeEvent
)


enum class TypeEvent {
    MOVIMENTATION, AUDIENCIA
}
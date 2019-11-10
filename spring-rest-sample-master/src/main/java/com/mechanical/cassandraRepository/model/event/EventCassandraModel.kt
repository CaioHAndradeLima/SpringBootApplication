package com.mechanical.cassandraRepository.model.event

import com.google.gson.annotations.SerializedName
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

const val NAME_TABLE = "eventProcessModel"
const val COLUMN_DATE_EVENT = "dateEvent"
const val COLUMN_ID_IDENTIFIER = "idIdentifier"

@Table(NAME_TABLE)
data class EventCassandraModel(
        @SerializedName("idIdentifier")
        @PrimaryKeyColumn(name = COLUMN_ID_IDENTIFIER, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        val idIdentifier: String,

        @SerializedName("dateEvent")
        @PrimaryKeyColumn(name = COLUMN_DATE_EVENT, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        val dateEvent: Long,

        @SerializedName("value")
        @PrimaryKeyColumn(name = "value", type = PrimaryKeyType.CLUSTERED)
        val value: String,

        @SerializedName("numberProcess")
        val numberProcess: String,

        @SerializedName("type")
        val type: TypeEvent
)

enum class TypeEvent {
    MOVIMENTATION, AUDIENCIA
}
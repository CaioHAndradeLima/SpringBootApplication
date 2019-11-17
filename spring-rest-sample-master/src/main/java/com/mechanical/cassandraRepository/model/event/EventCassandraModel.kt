package com.mechanical.cassandraRepository.model.event

import com.google.gson.annotations.SerializedName
import com.mechanical.infix_utils.toJson
import com.mechanical.provider.provideGson
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

const val NAME_TABLE = "eventModel"
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

        @SerializedName("numberProcess")
        val numberProcess: String,

        //infos to search on db the entity EventProcessCassandraModel
        private val eventProcessInfoModelString: String
) {

    constructor(idIdentifier: String, dateEvent: Long, numberProcess: String, eventProcessInfoModel: EventProcessInfoModel)
            : this(idIdentifier, dateEvent, numberProcess, eventProcessInfoModel.toJson())

    fun getEventProcessInfoModel() = provideGson()
            .fromJson(eventProcessInfoModelString, EventProcessInfoModel::class.java)
}

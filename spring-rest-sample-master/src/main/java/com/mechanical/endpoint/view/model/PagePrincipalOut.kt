package com.mechanical.endpoint.view.model

import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.model.event.EventCassandraModel
import com.mechanical.cassandraRepository.model.event.EventProcessCassandraModel

data class PagePrincipalOut(
        @SerializedName("isNeededShowAddProcess") val isNeededShowAddProcess :Boolean = false,
        @SerializedName("isPossibleShowListEvents") val isPossibleShowListEvents:Boolean = false,
        @SerializedName("listEvents") val listEvents: MutableList<EventProcessCassandraModel>? = null
)
package com.mechanical.cassandraRepository.model.event

import com.google.gson.annotations.SerializedName
import java.util.*

data class EventProcessInfoModel(
        @SerializedName("uuid")
        val uuid: UUID
)
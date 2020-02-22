package com.mechanical.cassandraRepository

import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.model.WorkerCassandraModel
import com.mechanical.endpoint.gson

data class WorkerSession(
        @SerializedName("workerSession") var worker: WorkerCassandraModel
) : EntityBase()


abstract class EntityBase {
    fun toJson() : String = gson.toJson(this)
}
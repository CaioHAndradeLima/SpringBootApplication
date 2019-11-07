package com.mechanical.cassandraRepository

import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.endpoint.gson

data class User(
        @SerializedName("user") var user: UserCassandraModel,
        var lawOffice: LawOffice? = null
) : EntityBase()


abstract class EntityBase {
    fun toJson() : String = gson.toJson(this)
}
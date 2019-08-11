package com.mechanical.cassandraRepository

import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.endpoint.gson

data class User(
        var user: UserCassandraModel
) : EntityBase()


abstract class EntityBase {
    fun toJson() : String = gson.toJson(this)
}
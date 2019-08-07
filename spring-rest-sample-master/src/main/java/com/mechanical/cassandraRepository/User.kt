package com.mechanical.cassandraRepository

import com.mechanical.cassandraRepository.model.AddressUserCassandraModel
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.endpoint.gson

data class User(
        var user: UserCassandraModel,
        var address: AddressUserCassandraModel?
) : EntityBase()


abstract class EntityBase {
    fun toJson() = gson.toJson(this)
}
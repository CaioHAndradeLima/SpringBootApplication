package com.mechanical.cassandraRepository

import com.mechanical.cassandraRepository.model.AddressUserCassandraModel
import com.mechanical.cassandraRepository.model.UserCassandraModel

data class User(
        var user: UserCassandraModel,
        var address: AddressUserCassandraModel?
)
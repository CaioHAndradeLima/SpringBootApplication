package com.mechanical.model

import com.mechanical.apiescavador.`in`.FREQUENCY
import com.mechanical.cassandraRepository.impl.ManagerProcessResponse
import com.mechanical.cassandraRepository.model.UserCassandraModel

data class RoutineModel(
        var processInfo: ManagerProcessResponse,
        val isNeededRegister: Boolean,
        val user: UserCassandraModel,
        val frequency: FREQUENCY?
)
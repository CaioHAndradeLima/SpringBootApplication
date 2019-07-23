package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.AddressUserCassandraModel
import org.jetbrains.annotations.Nullable
import org.springframework.data.cassandra.repository.AllowFiltering
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressUserRepository : CassandraRepository<AddressUserCassandraModel, UUID> {

    @AllowFiltering
    @Nullable
    fun findByUuid(uuidAddress: UUID): AddressUserCassandraModel?
}
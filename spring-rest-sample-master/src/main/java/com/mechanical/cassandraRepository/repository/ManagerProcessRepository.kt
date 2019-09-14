package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.model.ManagerProcessCassandraModel
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ManagerProcessRepository : CassandraRepository<ManagerProcessCassandraModel, String> {

    fun findByUuid(uuid: UUID): ManagerProcessCassandraModel

/*
    @AllowFiltering
    @Nullable
    @Query("SELECT u FROM LawOffice u WHERE u.latitude > 23.4334 and u.longitude < 46.353535")
    fun findByLocal() */

}
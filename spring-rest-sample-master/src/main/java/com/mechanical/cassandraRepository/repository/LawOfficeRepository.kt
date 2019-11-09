package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.LawOffice
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LawOfficeRepository : CassandraRepository<LawOffice, String> {

    fun findByCpfOwner(cpfOwner: String): List<LawOffice>

    fun findByCpfOwnerAndUuid(cpfOwner: String, uuid: UUID): LawOffice
/*
    @AllowFiltering
    @Nullable
    @Query("SELECT u FROM LawOffice u WHERE u.latitude > 23.4334 and u.longitude < 46.353535")
    fun findByLocal() */

}
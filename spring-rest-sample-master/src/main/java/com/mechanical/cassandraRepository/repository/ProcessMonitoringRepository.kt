package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.ProcessMonitoringCassandraModel
import org.jetbrains.annotations.Nullable
import org.springframework.data.cassandra.repository.AllowFiltering
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcessMonitoringRepository : CassandraRepository<ProcessMonitoringCassandraModel,String>{

    @AllowFiltering
    @Nullable
    fun findByNumberProcess(numberProcess: String) : ProcessMonitoringCassandraModel?

}
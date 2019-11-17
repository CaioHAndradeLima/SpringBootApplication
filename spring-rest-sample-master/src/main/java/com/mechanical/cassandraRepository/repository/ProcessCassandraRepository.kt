package com.mechanical.cassandraRepository.repository

import com.mechanical.cassandraRepository.model.ProcessCassandraModel
import org.springframework.data.cassandra.repository.CassandraRepository

interface ProcessCassandraRepository: CassandraRepository<ProcessCassandraModel,String> {
    fun findByNumberProcess(numberProcess: String) : ProcessCassandraModel?
}
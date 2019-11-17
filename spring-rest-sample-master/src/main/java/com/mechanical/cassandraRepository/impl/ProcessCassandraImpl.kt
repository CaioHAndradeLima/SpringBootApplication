package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.model.ProcessCassandraModel
import com.mechanical.cassandraRepository.repository.ProcessCassandraRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProcessCassandraImpl {

    @Autowired(required = true)
    lateinit var repository: ProcessCassandraRepository

    fun save(pcm: ProcessCassandraModel) {
        repository.save(pcm)
    }

    fun searchByNumberProcess(numberProcess: String) = repository.findByNumberProcess(numberProcess)
}
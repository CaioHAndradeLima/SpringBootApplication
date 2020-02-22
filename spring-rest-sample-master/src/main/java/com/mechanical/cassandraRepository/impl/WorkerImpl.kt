package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.extensions.isJustNumber
import com.mechanical.cassandraRepository.model.WorkerCassandraModel
import com.mechanical.cassandraRepository.repository.WorkerRepository
import com.mechanical.endpoint.LoginEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class WorkerImpl {

    @Autowired(required = true)
    lateinit var workerRepository: WorkerRepository

    fun getAllUser(loginEntity: LoginEntity): WorkerSession? {
        val user = getAllUser(loginEntity.emailOrCPF)
                ?: return null

        if (user.worker.password != loginEntity.password) return null

        return user
    }


    private fun getAllUser(email: String): WorkerSession? {
        val userModel = workerRepository.findByEmail(email) ?: return null

        return WorkerSession(userModel)
    }

    fun saveUser(workerSession: WorkerSession): Boolean {
        getAllUser(workerSession.worker.email)?.let { return false }

        workerRepository.save(workerSession.worker)

        return true
    }

}
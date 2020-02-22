package com.mechanical.provider

import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.model.WorkerCassandraModel
import com.mechanical.security.AuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object WorkerProvider {

    fun provideUserAuthenticate(): WorkerCassandraModel? {
        return provideUser()?.worker
    }


    fun provideUser(): WorkerSession? {

        if (SecurityContextHolder.getContext().authentication != null) {
            return (SecurityContextHolder.getContext().authentication as AuthenticationToken).authenticatedUser.workerSession
        }

        return null
    }

}

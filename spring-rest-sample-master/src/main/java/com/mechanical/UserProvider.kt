package com.mechanical

import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.security.AuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object UserProvider {

    fun provideUserAuthenticate(): UserCassandraModel? {

        if(SecurityContextHolder.getContext().authentication != null) {
            return (SecurityContextHolder.getContext().authentication as AuthenticationToken).authenticatedUser.user.user;
        }

        return null;
    }
}

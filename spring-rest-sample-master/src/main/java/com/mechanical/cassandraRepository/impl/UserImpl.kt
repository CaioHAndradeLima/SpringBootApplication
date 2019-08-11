package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.extensions.isJustNumber
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LoginEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserImpl {

    @Autowired(required = true)
    lateinit var userRepository: UserRepository

    fun getAllUser(loginEntity: LoginEntity): User? {
        val user = getAllUser(loginEntity.emailOrCPF)
                ?: return null

        if (user.user.password != loginEntity.password) return null

        return user
    }


    private fun getAllUser(cpfOrEmail: String): User? {
        val userModel = (if (cpfOrEmail.isJustNumber()) {
            userRepository.findBycpf(cpfOrEmail)
        } else {
            userRepository.findByEmail(cpfOrEmail)
        }) ?: return null

        return User(userModel)
    }

    fun saveUser(user: User): Boolean {
        getAllUser(user.user.cpf)?.let { return false }
        getAllUser(user.user.email)?.let { return false }

        userRepository.save(user.user)

        return true
    }

}